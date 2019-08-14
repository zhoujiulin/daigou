package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumTypeArticle;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.common.utils.DaigouUtil;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.ArticleService;
import xiaolan.daigou.service.ColisService;

@Service
public class ColisServiceImpl extends AbstractServiceImpl<Colis> implements ColisService {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ColisDao colisDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private StockageDao stockageDao;
	
    public ColisServiceImpl() {
        super(Colis.class);
    }
	
	@Override
	public Colis createColis(Long idUser) {
		String nameColis = "Colis 1";
		Colis lastColis = this.colisDao.getLastColis(idUser);
		if(lastColis != null) {
			String index = lastColis.getNameColis().replace("Colis ", "");
			nameColis = "Colis " + (Integer.valueOf(index) +  1); 
		}
		
		Utilisateur utilisateur = utilisateurDao.findById(idUser);
		
		Colis colis = new Colis();
		colis.setStatusColis(EnumStatusColis.COLIS_NON_ENVOYE);
		colis.setUtilisateur(utilisateur);
		colis.setNameColis(nameColis);
		return this.colisDao.save(colis);
	}

	@Override
	public List<Colis> getColisByStatus(int status, Long idUser) {
		return colisDao.getColisByStatus(status, idUser);
	}

	@Override
	public Colis envoyerColis(Colis colis) {
		for(Article article : colis.getArticles()) {
			article.setColis(colis);
			article.setDateEnvoie(new Date());
		}
		this.computeCommandePourEnvoyerColis(colis);
		
		colis = this.colisDao.findById(colis.getIdColis());
		colis.setStatusColis(EnumStatusColis.COLIS_ENVOYE_VERS_CHINE);
		colis.setDateEnvoyer(new Date());
		colis = colisDao.save(colis);
		
		return colis;
	}
	
	@Override
	public Colis arriverColis(Colis colis) {
		this.computeCommandeClientPourArriverColis(colis);
		
		colis = this.colisDao.findById(colis.getIdColis());
		colis.setDateArriver(new Date());
		colis.setStatusColis(EnumStatusColis.COLIS_ARRIVE_EN_CHINE);
		
		colis = colisDao.save(colis);
		return colis;
	}

	private void computeCommandeClientPourArriverColis(Colis colis) {
		List<Commande> commandesClient = new ArrayList<Commande>(); 
		List<Commande> commandesStockage = new ArrayList<Commande>(); 
		
		for(Article article : colis.getArticles()) {
			Commande commande = this.commandeDao.findById(article.getCommande().getId());
			
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_CLIENT) {
				if(!commandesClient.contains(commande)){
					commande.setStatus(DaigouUtil.isCommandeArriveePretADistribuer(commande));
					commandesClient.add(commande);
				}
			}
			
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE) {
				if(!commandesStockage.contains(commande)){
					commandesStockage.add(commande);
				}
				
				ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), colis.getUtilisateur().getIdUser());
				articleStockage.setCountStockageChine(articleStockage.getCountStockageChine() + article.getCount());
				articleStockage.setCountStockageEnRoute(articleStockage.getCountStockageEnRoute() - article.getCount());
				this.stockageDao.save(articleStockage);
			}
		}
		
		this.updateStatusCommandeForFullSendCommande(commandesClient, colis, EnumStatusCommande.COMMANDE_PRET_A_DISTRIBUER);
		for(Commande commande : commandesStockage) {
			colis = this.colisDao.findById(colis.getIdColis());
			for(Article a : commande.getArticles()) {
				a.setColis(colis);
				a.setCommande(null);
			}
			commande.setArticles(null);
			this.commandeDao.delete(commande);
		}
	}
	
	private void computeCommandePourEnvoyerColis(Colis colis) {
		List<Commande> fullSendCommandes = new ArrayList<Commande>(); 
		List<Commande> partSendCommande = new ArrayList<Commande>(); 
		List<Article> articlesStockages = new ArrayList<Article>(); 
		
		for(Article article : colis.getArticles()) {
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_CLIENT) {
				Commande commande = this.commandeDao.findById(article.getCommande().getId());
				
				if(!fullSendCommandes.contains(commande) && !partSendCommande.contains(commande)) {
					boolean isFullSend = isFullSendCommandes(commande, article, colis);
					if(isFullSend) {
						fullSendCommandes.add(commande);
					}else {
						partSendCommande.add(commande);
					}
				}
			}
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE) {
				articlesStockages.add(article);
			}
		}
		
		this.updateStatusCommandeForFullSendCommande(fullSendCommandes, colis, EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);
		this.updateStatusCommandeForPartSendCommande(partSendCommande, colis);
		this.envoyerArticlesStockages(articlesStockages, colis);
	}
	
	private void envoyerArticlesStockages(List<Article> articles, Colis colis) {
		colis = this.colisDao.findById(colis.getIdColis());
		Utilisateur utilisateur = colis.getUtilisateur();
		for(Article a : articles) {
			ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(a.getNameArticle(), utilisateur.getIdUser());
			articleStockage.setCountStockageEnRoute(articleStockage.getCountStockageEnRoute() + a.getCount());
			articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() - a.getCount());
			this.stockageDao.save(articleStockage);
		}
		
		Commande commande = new Commande();
		commande.setStatus(EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);
		commande.setTypeCommande(EnumTypeCommande.COMMANDE_STOCKAGE);
		commande.getArticles().addAll(articles);
		commande.setUtilisateur(utilisateur);
		
		for(Article a : articles) {
			a.setColis(colis);
			a.setCommande(commande);
		}
		
		this.commandeDao.save(commande);
	}

	private void updateStatusCommandeForPartSendCommande(List<Commande> partSendCommande, Colis colis) {
		for(Commande commande : partSendCommande) {
			Commande newCommande = DaigouUtil.createNewObjectCommande(commande, colis.getUtilisateur(), EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);
			
			for (Iterator<Article> it = commande.getArticles().iterator(); it.hasNext();) {
				Article article = it.next();
				if(article.getColis() != null && article.getColis().getIdColis().longValue() == colis.getIdColis().longValue()) {
					
					article.setCommande(newCommande);
					if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
						Article newArticle = (Article) DaigouUtil.cloneObject(article);
						newArticle.setIdArticle(null);
						newArticle.setCount(newArticle.getCountArticleAchete());
						newArticle.setStatusArticle(EnumStatusArticle.PREPARE_TOUT);
						
						int newCount = article.getCount() - article.getCountArticleAchete();
						for(Article ar : commande.getArticles()) {
							if(ar.getIdArticle() == article.getIdArticle()) {
								//modifier ancien article
								ar.setCount(newCount);
								ar.setCountArticleAchete(0);
								ar.setStatusArticle(EnumStatusArticle.NON_PREPARE);
								ar.setCommande(commande);
								ar.setColis(null);
							}
						}
						newCommande.getArticles().add(newArticle);
					}else {
						newCommande.getArticles().add(article);
						//remove ancien article
						it.remove();
					}
				}
			} 
			
			this.commandeDao.save(DaigouUtil.computeStatusCommande(commande));
			this.commandeDao.save(newCommande);
		}
	}

	private void updateStatusCommandeForFullSendCommande(List<Commande> fullSendCommandes, Colis colis, EnumStatusCommande newStatus) {
		for(Commande commande : fullSendCommandes) {
			boolean isHasArticlePreparePartie = false;
			for(Article article : commande.getArticles()) {
				if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
					isHasArticlePreparePartie = true;
				}
			}
			
			if(isHasArticlePreparePartie) {
				Commande newCommande = DaigouUtil.createNewObjectCommande(commande, colis.getUtilisateur(), newStatus);

				for (Iterator<Article> it = commande.getArticles().iterator(); it.hasNext();) {
					Article article = it.next();
					if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
						Article newArticle = (Article) DaigouUtil.cloneObject(article);
						newArticle.setIdArticle(null);
						newArticle.setCount(newArticle.getCountArticleAchete());
						newArticle.setStatusArticle(EnumStatusArticle.PREPARE_TOUT);
						newArticle.setCommande(newCommande);
						
						int newCount = article.getCount() - article.getCountArticleAchete();
						for(Article ar : commande.getArticles()) {
							if(ar.getIdArticle() == article.getIdArticle()) {
								//modifier ancien article
								ar.setCount(newCount);
								ar.setCountArticleAchete(0);
								ar.setStatusArticle(EnumStatusArticle.NON_PREPARE);
								ar.setCommande(commande);
								ar.setColis(null);
							}
						}
						newCommande.getArticles().add(newArticle);
					}
					if(article.getStatusArticle() == EnumStatusArticle.PREPARE_TOUT) {
						Article newArticle = (Article) DaigouUtil.cloneObject(article);
						newArticle.setCommande(newCommande);
						newArticle.setIdArticle(null);
						newCommande.getArticles().add(newArticle);
						
						article.setCommande(null);
						article.setColis(null);
						it.remove();
					}
				}
				this.commandeDao.save(newCommande);
				this.commandeDao.save(DaigouUtil.computeStatusCommande(commande));
			}else {
				commande.setStatus(newStatus);
				this.commandeDao.save(commande);
			}
			
		}
	}

	private boolean isFullSendCommandes(Commande commande, Article article, Colis colis) {
		boolean isFullSend = true;
		for(Article a : commande.getArticles()) {
			if(a.getColis() == null) {
				isFullSend = false;
			}
			if(a.getColis() != null && a.getColis().getIdColis().longValue() != colis.getIdColis().longValue()) {
				isFullSend = false;
			}
		}
		
		return isFullSend;
	}

	@Override
	public Article putArticleStockageInColis(ArticleStockage articleStockage, int idColis, int countArticleStockage) {
		Article article = null;
		boolean isArticleExistsInColis = false;
		Colis colis = this.colisDao.findById(Long.valueOf(idColis));
		for(Article a : colis.getArticles()) {
			if(a.getNameArticle().equals(articleStockage.getNameArticleStockage())) {
				isArticleExistsInColis = true;
				article = a;
			}
		}
		if(isArticleExistsInColis) {
			article.setCount(article.getCount() + countArticleStockage);
			//article.setCountArticleFromStockageFrance(article.getCountArticleFromStockageFrance() + countArticleStockage);
			article.setColis(colis);
		}else {
			article = new Article();
			article.setNameArticle(articleStockage.getNameArticleStockage());
			article.setTypeArticle(EnumTypeArticle.ARTICLE_STOCKAGE);
			article.setStatusArticle(EnumStatusArticle.STOCKAGE);
			article.setDateCreation(new Date());
			//article.setCountArticleFromStockageFrance(countArticleStockage);
			article.setCount(countArticleStockage);
			article.setColis(colis);
		}
		
		article = this.articleService.save(article);
		
		articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() + countArticleStockage);
		articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() - countArticleStockage);
		this.stockageDao.save(articleStockage);
		
		return article;
	}
	
	@Override
	public BaseDao<Colis> getDao() {
		return this.colisDao;
	}

}
