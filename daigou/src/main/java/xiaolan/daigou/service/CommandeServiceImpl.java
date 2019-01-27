package xiaolan.daigou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;

@Service
public class CommandeServiceImpl implements CommandeService{

	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private ClientDao clientDao;

	@Override
	public Commande createNewCommande(Commande commande, Long userId) {
		Utilisateur utilisateur = utilisateurDao.findById(userId);
		
		Client client = clientDao.findClientByName(commande.getClient(), userId);
		if(client != null) {
			commande.setClient(client);
		}
		
		commande.getClient().setUtilisateur(utilisateur);
		
		commande.setDateCreation(new Date());
		commande.setStatus(EnumStatusCommande.NEW_COMMANDE.getIndex());
		commande.setUtilisateur(utilisateur);
		
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public Commande updateCommande(Commande commande) {
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public Commande findCommandeById(Long id) {

		return this.commandeDao.findById(id);
	}

	@Override
	public List<Commande> getCommandesByStatus(List<String> statusList, Long userId) {
		List<Commande> commandes = this.commandeDao.getCommandesByStatus(statusList, userId);
		return commandes;
	}

	@Override
	public Map<Integer, Map<String, String>> getCommandeStatus() {
		Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>();
		EnumStatusCommande[] statusList = EnumStatusCommande.values();
		
		for(EnumStatusCommande status : statusList) {
			Map<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(status.getValue(), status.getColor());
			
			map.put(status.getIndex(), valueMap);
		}
		return map;
	}
	
	@Override
	public Map<String, String> getCommandeStatusGroup() {
		Map<String, String> map = new HashMap<String, String>();
		EnumStatusCommande[] statusList = EnumStatusCommande.values();
		
		EnumStatusCommandeGroup[] statusGroupList = EnumStatusCommandeGroup.values();
		for(EnumStatusCommandeGroup group : statusGroupList) {
			StringBuilder sb = new StringBuilder();
			
			for(EnumStatusCommande status : statusList) {
				if(status.isInGroup(group)) {
					sb.append(status.getIndex());
					sb.append(",");
				}
			}
			
			String text = sb.toString().substring(0, sb.length() - 1);
			map.put(text, group.getValue());
		}
		return map;
	}

	@Override
	public Map<Integer, Map<String, String>> getArticleStatus() {
		Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>();
		EnumStatusArticle[] statusList = EnumStatusArticle.values();
		for(EnumStatusArticle status : statusList) {
			Map<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(status.getValue(), status.getColor());
			map.put(status.getIndex(), valueMap);
		}
		return map;
	}
	
	@Override
	public Commande saveCommande(Commande commande) {
		List<Article> articles = computeStatusArticle(commande.getArticles());
		commande.setArticles(articles);
		
		commande = computeStatusCommande(commande);
		
		return this.commandeDao.save(commande);
	}
	
	private List<Article> computeStatusArticle(List<Article> articles){
		List<Article> articleList = new ArrayList<Article>();
		
		for(Article article : articles) {
			int count = article.getCount();
			int countAchete = article.getCountArticleAchete();
			int countFromStockageEnFrance = article.getCountArticleFromStockageFrance();
			int countFromStockageEnChine = article.getCountArticleFromStockageChine();
			int countFromStockageEnRoute = article.getCountArticleFromStockageEnRoute();
			
			int countPrepare = countAchete + countFromStockageEnFrance + countFromStockageEnChine + countFromStockageEnRoute;
			if(count == countPrepare) {
				article.setStatusArticle(EnumStatusArticle.PREPARE_BIEN.getIndex());
			}else if(count < countPrepare) {
				article.setStatusArticle(EnumStatusArticle.QTE_INCORRECT.getIndex());
			}else if(count > countPrepare && countPrepare != 0) {
				article.setStatusArticle(EnumStatusArticle.PREPARE_PARTIE.getIndex());
			}else {
				article.setStatusArticle(EnumStatusArticle.NON_PREPARE.getIndex());
			}
			
			articleList.add(article);
		}
		
		return articleList;
	}
	
	private Commande computeStatusCommande(Commande commande) {
		EnumStatusCommande statusCommande = EnumStatusCommande.COMMANDE_PRET_A_ENVOYER;
		
		boolean isArticleNonPrepare = true;
		for(Article article : commande.getArticles()) {
			if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE.getIndex()) {
				statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET;
			}
			if(article.getStatusArticle() != EnumStatusArticle.NON_PREPARE.getIndex()) {
				isArticleNonPrepare = false;
			}
		}
		
		if(isArticleNonPrepare) {
			statusCommande = EnumStatusCommande.NEW_COMMANDE;
		}
		
		if(statusCommande == EnumStatusCommande.COMMANDE_PRET_A_ENVOYER) {
			String nameWechat = commande.getClient().getNameWechat();
			String nameLivraison = commande.getClient().getNameLivraison();
			String telephone = commande.getClient().getTelephone();
			String adresse = commande.getClient().getAdresse();
			if(nameWechat == null || "".equals(nameWechat) || nameLivraison == null || "".equals(nameLivraison) || telephone == null || "".equals(telephone) || adresse == null || "".equals(adresse)) {
				statusCommande = EnumStatusCommande.COMMANDE_MANQUE_INFO_CLIENT;
			}
		}
		
		commande.setStatus(statusCommande.getIndex());
		
		return commande;
	}

	@Override
	public void deleteCommandeById(long idCommande) {
		this.commandeDao.deleteById(idCommande);
	}
}
