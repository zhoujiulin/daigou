package xiaolan.daigou.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.utils.DaigouUtil;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.ColisService;

@Service
public class ColisServiceImpl extends AbstractServiceImpl<Colis> implements ColisService {

	@Autowired
	private ColisDao colisDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private CommandeDao commandeDao;
	
    public ColisServiceImpl() {
        super(Colis.class);
    }
	
	@Override
	public Colis createColis(Colis colis, Long idUser) {
		Utilisateur utilisateur = utilisateurDao.findById(idUser);
		colis.setUtilisateur(utilisateur);
		if(colis.getNameColis() == null || colis.getNameColis().equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(new Date());
			colis.setNameColis(date);
		}
		colis.setStatusColis(EnumStatusColis.COLIS_NON_ENVOYE);
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
		this.computeCommandePourArriverColis(colis);
		
		colis = this.colisDao.findById(colis.getIdColis());
		colis.setDateArriver(new Date());
		colis.setStatusColis(EnumStatusColis.COLIS_ARRIVE_EN_CHINE);
		
		colis = colisDao.save(colis);
		return colis;
	}
	
	private void computeCommandePourArriverColis(Colis colis) {
		List<Commande> commandes = new ArrayList<Commande>(); 
		
		for(Article article : colis.getArticles()) {
			Commande commande = this.commandeDao.findById(article.getCommande().getId());

			if(!commandes.contains(commande)){
				commande.setStatus(DaigouUtil.isCommandeArriveePretADistribuer(commande));
				commandes.add(commande);
			}
		}
		
		this.updateStatusCommandeForFullSendCommande(commandes, colis);
	}
	
	private void computeCommandePourEnvoyerColis(Colis colis) {
		List<Commande> fullSendCommandes = new ArrayList<Commande>(); 
		List<Commande> partSendCommande = new ArrayList<Commande>(); 
		
		for(Article article : colis.getArticles()) {
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
		
		this.updateStatusCommandeForFullSendCommande(fullSendCommandes, colis);
		this.updateStatusCommandeForPartSendCommande(partSendCommande, colis);
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

	private void updateStatusCommandeForFullSendCommande(List<Commande> fullSendCommandes, Colis colis) {
		for(Commande commande : fullSendCommandes) {
			boolean isHasArticlePreparePartie = false;
			for(Article article : commande.getArticles()) {
				if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
					isHasArticlePreparePartie = true;
				}
			}
			
			if(isHasArticlePreparePartie) {
				Commande newCommande = DaigouUtil.createNewObjectCommande(commande, colis.getUtilisateur(), EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);

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
				commande.setStatus(EnumStatusCommande.COMMANDE_SUR_LA_ROUTE);
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
	public BaseDao<Colis> getDao() {
		return this.colisDao;
	}
}
