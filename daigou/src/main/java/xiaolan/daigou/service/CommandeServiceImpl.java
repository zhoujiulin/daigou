package xiaolan.daigou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.domain.entity.Commande;

@Service
public class CommandeServiceImpl implements CommandeService{

	@Autowired
	private CommandeDao commandeDao;

	@Override
	public Commande createCommande(Commande commande) {
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public Commande updateCommande(Commande commande) {
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public List<Commande> findAll() {
		
		return this.commandeDao.getAll();
	}

	@Override
	public Commande findCommandeById(Long id) {

		return this.commandeDao.findById(id);
	}

	@Override
	public List<Commande> getCommandesByStatus(List<Integer> statusList) {
		List<Commande> commandes = this.commandeDao.getCommandesByStatus(statusList);
		return commandes;
	}
}
