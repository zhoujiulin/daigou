package xiaolan.daigou.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.ColisService;

@Service
public class ColisServiceImpl implements ColisService {

	@Autowired
	private ColisDao colisDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private CommandeDao commandeDao;

	@Override
	public Colis createColis(Colis colis, Long idUser) {
		Utilisateur utilisateur = utilisateurDao.findById(idUser);
		colis.setUtilisateur(utilisateur);
		if(colis.getNameColis() == null || colis.getNameColis().equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(new Date());
			colis.setNameColis(date);
		}
		colis.setStatusColis(EnumStatusColis.COLIS_NON_ENVOYE.getIndex());
		return this.colisDao.save(colis);
	}

	@Override
	public Map<Integer, String> getColisStatus() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		for(EnumStatusColis e : EnumStatusColis.values()) {
			map.put(e.getIndex(), e.getValue());
		}
		
		return map;
	}
	@Override
	public List<Colis> getColisByStatus(int status, Long idUser) {
		return colisDao.getColisByStatus(status, idUser);
	}

	@Override
	public Colis envoyerColis(Colis colis) {
		colis.setStatusColis(EnumStatusColis.COLIS_ENVOYE_VERS_CHINE.getIndex());
		for(Article article : colis.getArticles()) {
			article.setColis(colis);
		}
		
		colis = colisDao.save(colis);
		
		this.computeCommandePourEnvoyerColis(colis);
		return colis;
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
		
		this.updateStatusCommandeForFullSendCommande(fullSendCommandes);
		this.updateStatusCommandeForPartSendCommande(partSendCommande, colis);
	}
	
	private void updateStatusCommandeForPartSendCommande(List<Commande> partSendCommande, Colis colis) {
		for(Commande commande : partSendCommande) {
			Commande newCommande = new Commande();
			newCommande.setClient(commande.getClient());
			newCommande.setStatus(EnumStatusCommande.COMMANDE_SUR_LA_ROUTE.getIndex());
			newCommande.setTypeCommande(commande.getTypeCommande());
			
			
			for(Article article : commande.getArticles()) {
				if(article.getColis() != null && article.getColis().getIdColis() == colis.getIdColis()) {
					newCommande.getArticles().add(article);
					
					commande.getArticles().remove(article);
				}
			}
			
			this.commandeDao.save(commande);
			this.commandeDao.save(newCommande);
		}
	}

	private void updateStatusCommandeForFullSendCommande(List<Commande> fullSendCommandes) {
		for(Commande commande : fullSendCommandes) {
			commande.setStatus(EnumStatusCommande.COMMANDE_SUR_LA_ROUTE.getIndex());
			this.commandeDao.save(commande);
		}
	}

	private boolean isFullSendCommandes(Commande commande, Article article, Colis colis) {
		boolean isFullSend = true;
		for(Article a : commande.getArticles()) {
			if(a.getColis() == null) {
				isFullSend = false;
			}
			if(a.getColis() != null && a.getColis().getIdColis() != colis.getIdColis()) {
				isFullSend = false;
			}
		}
		
		return isFullSend;
	}
}
