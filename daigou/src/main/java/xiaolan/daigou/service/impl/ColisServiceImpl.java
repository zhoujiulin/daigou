package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.common.utils.DaigouUtils;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleMapEnRouteDTO;
import xiaolan.daigou.model.dto.ArticleMapEnRoutesDTO;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.ArticleStockage;
import xiaolan.daigou.model.entity.Colis;
import xiaolan.daigou.model.entity.Commande;
import xiaolan.daigou.model.entity.Utilisateur;
import xiaolan.daigou.model.enums.EnumStatusArticle;
import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.model.enums.EnumStatusColis;
import xiaolan.daigou.model.enums.EnumStatusCommande;
import xiaolan.daigou.model.enums.EnumTypeArticle;
import xiaolan.daigou.model.enums.EnumTypeCommande;
import xiaolan.daigou.model.exception.DaigouException;
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
	
    @Autowired
    protected Mapper dozerMapper;
	
	
    public ColisServiceImpl() {
        super(Colis.class);
    }
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public ColisDTO arriverColis(ArticleMapEnRoutesDTO articlesMapEnRoutesDTO) {
		
		this.computeCommandeClientPourArriverColis(articlesMapEnRoutesDTO);
		
		Colis colis = this.colisDao.findById(articlesMapEnRoutesDTO.getColis().getIdColis());
		colis.setDateArriver(new Date());
		colis.setStatusColis(EnumStatusColis.COLIS_ARRIVE_EN_CHINE);
		
		colis = colisDao.save(colis);

		return dozerMapper.map(colis, ColisDTO.class);
	}

	private void computeCommandeClientPourArriverColis(ArticleMapEnRoutesDTO articlesMapEnRoutesDTO) {
		Colis colis = dozerMapper.map(articlesMapEnRoutesDTO.getColis(), Colis.class);
		
		List<Commande> commandesClient = new ArrayList<Commande>(); 
		List<Commande> commandesStockage = new ArrayList<Commande>(); 
		
		for(Article article : colis.getArticles()) {
			Commande commande = this.commandeDao.findById(article.getCommande().getId());
			
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_CLIENT) {
				if(!commandesClient.contains(commande)){
					commande.setStatus(DaigouUtils.isCommandeArriveePretADistribuer(commande));
					for(Article a : commande.getArticles()) {
						a.setDateArrive(new Date());
					}
					commandesClient.add(commande);
				}
			}
			
			if(article.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE) {
				if(!commandesStockage.contains(commande)){
					commandesStockage.add(commande);
				}
				
				ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), colis.getUtilisateur().getIdUser());
				articleStockage.setCountStockageEnRoute(articleStockage.getCountStockageEnRoute() - article.getCount());
				articleStockage.setCountStockageChine(articleStockage.getCountStockageChine() + article.getCount());
				
				int countSelected = 0;
				for(ArticleMapEnRouteDTO articleMapEnRouteDTO : articlesMapEnRoutesDTO.getArticleMapEnRoutes()) {
					if(articleMapEnRouteDTO.getIdArticle().longValue() == article.getIdArticle().longValue()) {
						for(ArticleDTO articleDTO : articleMapEnRouteDTO.getArticles()) {
							countSelected = countSelected + articleDTO.getCountSelectedEnRouteToChine();
						}
					}
				}
				articleStockage.setCountStockageChineAvailable(articleStockage.getCountStockageChineAvailable() + article.getCount() - countSelected);
				articleStockage.setCountStockageEnRouteAvailable(articleStockage.getCountStockageChineAvailable() - article.getCount());
				
				this.stockageDao.save(articleStockage);
			}
		}
		
		this.updateStatusCommandeForFullSendCommande(commandesClient, colis, EnumStatusCommande.COMMANDE_PRET_A_DISTRIBUER, false);
		for(Commande commande : commandesStockage) {
			colis = this.colisDao.findById(colis.getIdColis());
			for(Article a : commande.getArticles()) {
				a.setColis(colis);
				a.setCommande(null);
				a.setDateArrive(new Date());
			}
			commande.setArticles(null);
			this.commandeDao.delete(commande);
		}
		
		//update stockage change en route to chine
		for(ArticleMapEnRouteDTO articleMapEnRouteDTO : articlesMapEnRoutesDTO.getArticleMapEnRoutes()) {
			for(ArticleDTO articleDTO : articleMapEnRouteDTO.getArticles()) {
				articleDTO.setCountArticleFromStockageChine(articleDTO.getCountArticleFromStockageChine() + articleDTO.getCountSelectedEnRouteToChine());
				articleDTO.setCountArticleFromStockageEnRoute(articleDTO.getCountArticleFromStockageEnRoute() - articleDTO.getCountSelectedEnRouteToChine());
				Article article = dozerMapper.map(articleDTO, Article.class);
				article.setCommande(this.commandeDao.findById(article.getCommande().getId()));
				this.articleService.save(article);
			}
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
		
		this.updateStatusCommandeForFullSendCommande(fullSendCommandes, colis, EnumStatusCommande.COMMANDE_SUR_LA_ROUTE, true);
		this.updateStatusCommandeForPartSendCommande(partSendCommande, colis, true);
		
		if(articlesStockages != null && !articlesStockages.isEmpty()) {
			this.envoyerArticlesStockages(articlesStockages, colis);
		}
	}
	
	private void envoyerArticlesStockages(List<Article> articles, Colis colis) {
		colis = this.colisDao.findById(colis.getIdColis());
		Utilisateur utilisateur = colis.getUtilisateur();
		for(Article a : articles) {
			
			if(a.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE) {
				ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(a.getNameArticle(), utilisateur.getIdUser());
				articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() - a.getCount());
				
				articleStockage.setCountStockageEnRouteAvailable(articleStockage.getCountStockageEnRouteAvailable() + a.getCount());
				articleStockage.setCountStockageEnRoute(articleStockage.getCountStockageEnRoute() + a.getCount());
				this.stockageDao.save(articleStockage);
			}
			
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

	private void updateStatusCommandeForPartSendCommande(List<Commande> partSendCommande, Colis colis, boolean isEnvoyer) {
		for(Commande commande : partSendCommande) {
			Commande newCommande = DaigouUtils.createNewObjectCommande(commande, colis.getUtilisateur(), EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);
			
			for (Iterator<Article> it = commande.getArticles().iterator(); it.hasNext();) {
				Article article = it.next();
				if(article.getColis() != null && article.getColis().getIdColis().longValue() == colis.getIdColis().longValue()) {
					
					article.setCommande(newCommande);
					if(article.getStatusArticlePreparation() == EnumStatusArticlePreparation.PREPARE_PARTIE) {
						Article newArticle = (Article) DaigouUtils.cloneObject(article);
						newArticle.setIdArticle(null);
						newArticle.setCount(newArticle.getCountArticleAchete());
						newArticle.setStatusArticlePreparation(EnumStatusArticlePreparation.PREPARE_TOUT);
						
						int newCount = article.getCount() - article.getCountArticleAchete();
						for(Article ar : commande.getArticles()) {
							if(ar.getIdArticle() == article.getIdArticle()) {
								//modifier ancien article
								ar.setCount(newCount);
								ar.setCountArticleAchete(0);
								ar.setCountArticleFromStockageFrance(0);
								ar.setCountArticleFromStockageEnRoute(0);
								ar.setCountArticleFromStockageChine(0);
								ar.setStatusArticlePreparation(EnumStatusArticlePreparation.NON_PREPARE);
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
			
			commande = DaigouUtils.computeStatusCommande(commande);
			commande = DaigouUtils.computeStatusArticle(commande);
			this.commandeDao.save(commande);
			
			this.updateArticleStockageFrancePourEnvoyerColis(newCommande, isEnvoyer);
			this.commandeDao.save(newCommande);
		}
	}

	private void updateStatusCommandeForFullSendCommande(List<Commande> fullSendCommandes, Colis colis, EnumStatusCommande newStatus, boolean isEnvoyer) {
		for(Commande commande : fullSendCommandes) {
			boolean isHasArticlePreparePartie = false;
			for(Article article : commande.getArticles()) {
				if(article.getStatusArticlePreparation() == EnumStatusArticlePreparation.PREPARE_PARTIE) {
					isHasArticlePreparePartie = true;
				}
			}
			newStatus = pretADistribuerOuManqueInfoClient(newStatus, commande);
			
			if(isHasArticlePreparePartie) {
				Commande newCommande = DaigouUtils.createNewObjectCommande(commande, colis.getUtilisateur(), newStatus);

				for (Iterator<Article> it = commande.getArticles().iterator(); it.hasNext();) {
					Article article = it.next();
					if(article.getStatusArticlePreparation() == EnumStatusArticlePreparation.PREPARE_PARTIE) {
						Article newArticle = (Article) DaigouUtils.cloneObject(article);
						newArticle.setIdArticle(null);
						newArticle.setCount(newArticle.getCountArticleAchete());
						newArticle.setCountArticleFromStockageFrance(newArticle.getCountArticleFromStockageFrance());
						newArticle.setStatusArticlePreparation(EnumStatusArticlePreparation.PREPARE_TOUT);
						newArticle.setCommande(newCommande);
						
						int newCount = article.getCount() - article.getCountArticleAchete();
						for(Article ar : commande.getArticles()) {
							if(ar.getIdArticle() == article.getIdArticle()) {
								//modifier ancien article
								ar.setCount(newCount);
								ar.setCountArticleAchete(0);
								ar.setCountArticleFromStockageFrance(0);
								ar.setCountArticleFromStockageEnRoute(0);
								ar.setCountArticleFromStockageChine(0);
								ar.setStatusArticlePreparation(EnumStatusArticlePreparation.NON_PREPARE);
								ar.setCommande(commande);
								ar.setColis(null);
							}
						}
						newCommande.getArticles().add(newArticle);
					}
					if(article.getStatusArticlePreparation() == EnumStatusArticlePreparation.PREPARE_TOUT) {
						Article newArticle = (Article) DaigouUtils.cloneObject(article);
						newArticle.setCommande(newCommande);
						newArticle.setIdArticle(null);
						newCommande.getArticles().add(newArticle);
						
						article.setCommande(null);
						article.setColis(null);
						it.remove();
					}
				}
				
				this.updateArticleStockageFrancePourEnvoyerColis(newCommande, isEnvoyer);
				this.commandeDao.save(newCommande);
				commande = DaigouUtils.computeStatusCommande(commande);
				commande = DaigouUtils.computeStatusArticle(commande);
				this.commandeDao.save(commande);
			}else {
				commande.setStatus(newStatus);
				commande = DaigouUtils.computeStatusArticle(commande);
				this.updateArticleStockageFrancePourEnvoyerColis(commande, isEnvoyer);

				this.commandeDao.save(commande);
			}
			
		}
	}
	
	private EnumStatusCommande pretADistribuerOuManqueInfoClient(EnumStatusCommande status, Commande commande) {
		if(status == EnumStatusCommande.COMMANDE_PRET_A_DISTRIBUER) {
			if(commande.getClient().getNameWechat() == null || commande.getClient().getNameWechat().equals("")
					|| commande.getClient().getTelephone() == null || commande.getClient().getTelephone().equals("")
					|| commande.getClient().getAdresse() == null || commande.getClient().getAdresse().equals("")) {
				status = EnumStatusCommande.COMMANDE_MANQUE_INFO_CLIENT;
			}
		}
		
		return status;
	}
	
	private void updateArticleStockageFrancePourEnvoyerColis(Commande commande, boolean isEnvoyer) {
		// update stockage france
		if(isEnvoyer) {
			for(Article article : commande.getArticles()) {
				if(article.getCountArticleFromStockageFrance() > 0) {
					ArticleStockage articleStockage = this.stockageDao.findByNameArticleStockage(article.getNameArticle(), commande.getUtilisateur().getIdUser());
					articleStockage.setCountStockageFranceReserve(articleStockage.getCountStockageFranceReserve() - article.getCountArticleFromStockageFrance());
					this.stockageDao.save(articleStockage);
				}
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public Article putArticleStockageInColis(ArticleStockageDTO articleStockageDTO, int idColis, int countArticleStockage) {
		Article article = null;
		boolean isArticleExistsInColis = false;
		Colis colis = this.colisDao.findById(Long.valueOf(idColis));
		for(Article a : colis.getArticles()) {
			if(a.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE && a.getNameArticle().equals(articleStockageDTO.getNameArticleStockage())) {
				isArticleExistsInColis = true;
				article = a;
			}
		}
		if(isArticleExistsInColis) {
			article.setCount(article.getCount() + countArticleStockage);
			article.setColis(colis);
		}else {
			article = new Article();
			article.setNameArticle(articleStockageDTO.getNameArticleStockage());
			article.setTypeArticle(EnumTypeArticle.ARTICLE_STOCKAGE);
			article.setStatusArticlePreparation(EnumStatusArticlePreparation.STOCKAGE);
			article.setStatusArticle(EnumStatusArticle.ARTICLE_NON_ENVOYE);
			article.setDateCreation(new Date());
			article.setCount(countArticleStockage);
			article.setColis(colis);
			article.setUtilisateur(colis.getUtilisateur());
		}
		
		article = this.articleService.save(article);
		
		articleStockageDTO.setCountStockageFranceColis(articleStockageDTO.getCountStockageFranceColis() + countArticleStockage);
		articleStockageDTO.setCountStockageFranceAvailable(articleStockageDTO.getCountStockageFranceAvailable() - countArticleStockage);
		
		ArticleStockage articleStockage = this.dozerMapper.map(articleStockageDTO, ArticleStockage.class);
		this.stockageDao.save(articleStockage);
		
		return article;
	}
	
	@Override
	public BaseDao<Colis> getDao() {
		return this.colisDao;
	}

}
