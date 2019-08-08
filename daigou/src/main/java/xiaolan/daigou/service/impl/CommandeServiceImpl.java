package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.CommandeService;

@Service
public class CommandeServiceImpl implements CommandeService{

	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ColisDao colisDao;

	@Override
	public Commande createNewCommande(Commande commande, Long userId) {
		Utilisateur utilisateur = utilisateurDao.findById(userId);
		
		if(commande.getTypeCommande() == EnumTypeCommande.COMMANDE_SANS_CLIENT.getIndex()) {
			commande.setClient(null);
		}else {
			Client client = clientDao.findClientByName(commande.getClient(), userId);
			if(client != null) {
				commande.setClient(client);
			}
			
			commande.getClient().setUtilisateur(utilisateur);
		}
		
		commande.setDateCreation(new Date());
		commande.setStatus(EnumStatusCommande.NEW_COMMANDE);
		commande.setUtilisateur(utilisateur);
		commande.setTypeCommande(EnumTypeCommande.COMMANDE_CLIENT.getIndex());
		
		for(Article a : commande.getArticles()) {
			a.setCommande(commande);
			a.setStatusArticle(computeStatusArticle(a));
		}
		
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
	public Commande saveCommande(Commande commande) {
		Set<Article> articles = computeStatusArticle(commande.getArticles());
		commande.setArticles(articles);
		
		commande = computeStatusCommande(commande);
		for(Article article : commande.getArticles()) {
			article.setCommande(commande);
		}
		return this.commandeDao.save(commande);
	}
	
	private Set<Article> computeStatusArticle(Set<Article> articles){
		Set<Article> articleList = new HashSet<Article>();
		
		for(Article article : articles) {
			article.setStatusArticle(computeStatusArticle(article));
			articleList.add(article);
		}
		
		return articleList;
	}
	
	private EnumStatusArticle computeStatusArticle(Article article){
		int count = article.getCount();
		int countAchete = article.getCountArticleAchete();
		int countFromStockageEnFrance = article.getCountArticleFromStockageFrance();
		int countFromStockageEnChine = article.getCountArticleFromStockageChine();
		int countFromStockageEnRoute = article.getCountArticleFromStockageEnRoute();
		
		int countPrepare = countAchete + countFromStockageEnFrance + countFromStockageEnChine + countFromStockageEnRoute;
		if(count == countPrepare) {
			return EnumStatusArticle.PREPARE_BIEN;
		}else if(count < countPrepare) {
			return EnumStatusArticle.QTE_INCORRECT;
		}else if(count > countPrepare && countPrepare != 0) {
			return EnumStatusArticle.PREPARE_PARTIE;
		}else {
			return EnumStatusArticle.NON_PREPARE;
		}
	}
	
	private Commande computeStatusCommande(Commande commande) {
		EnumStatusCommande statusCommande = EnumStatusCommande.COMMANDE_PRET_A_ENVOYER;
		
		boolean isArticleNonPrepare = true;
		boolean isArticlePretAEnvoyer = true;
		for(Article article : commande.getArticles()) {
			if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
				statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET;
				isArticlePretAEnvoyer = false;
			}
			if(article.getStatusArticle() != EnumStatusArticle.NON_PREPARE) {
				isArticleNonPrepare = false;
			}else {
				isArticlePretAEnvoyer = false;
			}
		}
		
		if(isArticleNonPrepare) {
			statusCommande = EnumStatusCommande.NEW_COMMANDE;
		}else {
			statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET;
		}
		
		if(isArticlePretAEnvoyer) {
			statusCommande = EnumStatusCommande.COMMANDE_PRET_A_ENVOYER;
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
		
		commande.setStatus(statusCommande);
		
		return commande;
	}

	@Override
	public void deleteCommandeById(long idCommande) {
		this.commandeDao.deleteById(idCommande);
	}
}
