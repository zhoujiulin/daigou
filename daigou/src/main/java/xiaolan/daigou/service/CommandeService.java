package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.entity.Commande;

public interface CommandeService {

	Commande createCommande(Commande commande);
	
	Commande updateCommande(Commande commande);
	
	List<Commande> findAll();
	
	Commande findCommandeById(Long id);
	
	List<Commande> getCommandesByStatus(List<Integer> statusList);
}
