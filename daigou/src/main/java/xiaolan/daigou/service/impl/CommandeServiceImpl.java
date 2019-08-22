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
import xiaolan.daigou.common.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.common.enums.EnumTypeArticle;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.common.utils.DaigouUtils;
import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.CommandeService;

@Service
public class CommandeServiceImpl extends AbstractServiceImpl<Commande> implements CommandeService{

	@Autowired
	private StockageDao stockageDao;
	
	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ColisDao colisDao;
	
	@Autowired
	private ArticleDao articleDao;

    public CommandeServiceImpl() {
        super(Commande.class);
    }
	
	@Override
	public Commande createNewCommande(Commande commande, Long userId) {
		Utilisateur utilisateur = utilisateurDao.findById(userId);
		
		if(commande.getTypeCommande() == EnumTypeCommande.COMMANDE_STOCKAGE) {
			commande.setClient(null);
		}else {
			Client client = clientDao.findClientByName(commande.getClient(), userId);
			if(client != null) {
				commande.setClient(client);
			}
			
			commande.getClient().setUtilisateur(utilisateur);
		}
		
		commande.setStatus(EnumStatusCommande.NEW_COMMANDE);
		commande.setUtilisateur(utilisateur);
		commande.setTypeCommande(EnumTypeCommande.COMMANDE_CLIENT);
		
		for(Article a : commande.getArticles()) {
			a.setCommande(commande);
			a.setStatusArticlePreparation(DaigouUtils.computeStatusArticlePreparation(a));
			a.setTypeArticle(EnumTypeArticle.ARTICLE_CLIENT);
			a.setStatusArticle(EnumStatusArticle.ARTICLE_NON_ENVOYE);
			a.setDateCreation(new Date());
		}
		
		Commande c= this.commandeDao.save(commande);
		return c;
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
	public void deleteCommandeById(Long id) {
		this.computeCountStockageForDeleteCommande(id);
		this.deleteById(id);
	}
	
	@Override
	public Commande updateCommande(Commande commande) {
		Set<Article> articles = this.computeStatusArticle(commande.getArticles());
		commande.setArticles(articles);
		
		commande = DaigouUtils.computeStatusCommande(commande);
		commande = DaigouUtils.computeStatusArticle(commande);

		for(Article article : commande.getArticles()) {
			article.setCommande(commande);		
			if(article.getColis() != null) {
				Colis colis = colisDao.findById(article.getColis().getIdColis());
				article.setColis(colis);
			}
			
			if(article.getDateCreation() == null) {
				article.setDateCreation(new Date());
			}
		}
		this.computeCountStockageForUpdateCommande(commande);
		return this.commandeDao.save(commande);
	}
	
	private void computeCountStockageForDeleteCommande(Long idCommande) {
		Commande oldCommande = this.commandeDao.findById(idCommande);
		for(Article article : oldCommande.getArticles()) {
			ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), oldCommande.getUtilisateur().getIdUser());
			articleStockage.setCountStockageFranceReserve(articleStockage.getCountStockageFranceReserve() - article.getCountArticleFromStockageFrance());
			articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() + article.getCountArticleFromStockageFrance());
			articleStockage.setCountStockageEnRouteAvailable(articleStockage.getCountStockageEnRouteAvailable() + article.getCountArticleFromStockageEnRoute());
			articleStockage.setCountStockageChineAvailable(articleStockage.getCountStockageChineAvailable() + article.getCountArticleFromStockageChine());
			this.stockageDao.save(articleStockage);
		}
	}
	
	private void computeCountStockageForUpdateCommande(Commande commande) {
		Commande oldCommande = this.commandeDao.findById(commande.getId());
		
		for(Article article : commande.getArticles()) {
			ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), commande.getUtilisateur().getIdUser());
			Article oldArticle = null;
			for(Article a : oldCommande.getArticles()) {
				if(article.getIdArticle() != null && a.getIdArticle().longValue() == article.getIdArticle().longValue()) {
					oldArticle = a;
				}
			}
			
			// update stockage france
			int newCountFromStockageFrance = article.getCountArticleFromStockageFrance();
			if(oldArticle != null) {
				newCountFromStockageFrance = article.getCountArticleFromStockageFrance() - oldArticle.getCountArticleFromStockageFrance();
			}
			articleStockage.setCountStockageFranceReserve(articleStockage.getCountStockageFranceReserve() + newCountFromStockageFrance);
			articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() - newCountFromStockageFrance);
			
			// update stockage en route
			int newCountFromStockageEnRoute = article.getCountArticleFromStockageEnRoute();
			if(oldArticle != null) {
				newCountFromStockageEnRoute = article.getCountArticleFromStockageEnRoute() - oldArticle.getCountArticleFromStockageEnRoute();
			}
			articleStockage.setCountStockageEnRouteAvailable(articleStockage.getCountStockageEnRouteAvailable() - newCountFromStockageEnRoute);
			
			// update stockage chine
			int newCountFromStockageChine = article.getCountArticleFromStockageChine();
			if(oldArticle != null) {
				newCountFromStockageChine = article.getCountArticleFromStockageChine() - oldArticle.getCountArticleFromStockageChine();
			}
			articleStockage.setCountStockageChineAvailable(articleStockage.getCountStockageChineAvailable() - newCountFromStockageChine);
			
			this.stockageDao.save(articleStockage);
		}
		
		List<Article> articleSupprime = new ArrayList<Article>();
		for(Article oldArticle : oldCommande.getArticles()) {
			boolean isOldArticleExists = false;
			for(Article article : commande.getArticles()) {
				if(article.getIdArticle() != null && oldArticle.getIdArticle().longValue() == article.getIdArticle().longValue()) {
					isOldArticleExists = true;
				}
			}
			if(!isOldArticleExists) {
				articleSupprime.add(oldArticle);
			}
		}
		
		for(Article article : articleSupprime) {
			ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), commande.getUtilisateur().getIdUser());
			articleStockage.setCountStockageFranceReserve(articleStockage.getCountStockageFranceReserve() - article.getCountArticleFromStockageFrance());
			articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() + article.getCountArticleFromStockageFrance());
			articleStockage.setCountStockageEnRouteAvailable(articleStockage.getCountStockageEnRouteAvailable() + article.getCountArticleFromStockageEnRoute());
			articleStockage.setCountStockageChineAvailable(articleStockage.getCountStockageChineAvailable() + article.getCountArticleFromStockageChine());
			
			this.stockageDao.save(articleStockage);
		}
	}
	
	private Set<Article> computeStatusArticle(Set<Article> articles){
		Set<Article> articleList = new HashSet<Article>();
		
		for(Article article : articles) {
			if(article.getTypeArticle() == null) {
				article.setTypeArticle(EnumTypeArticle.ARTICLE_CLIENT);
			}
			
			article.setStatusArticlePreparation(DaigouUtils.computeStatusArticlePreparation(article));
			articleList.add(article);
		}
		
		return articleList;
	}

	@Override
	public BaseDao<Commande> getDao() {
		return this.commandeDao;
	}
}
