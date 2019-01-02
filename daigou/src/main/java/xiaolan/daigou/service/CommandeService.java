package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.domain.entity.Commande;

public interface CommandeService {

	Commande createNewCommande(Commande commande, Long userId);
	
	Commande updateCommande(Commande commande);
	
	List<Commande> findAll();
	
	Commande findCommandeById(Long id);
	
	List<Commande> getCommandesByStatus(List<String> statusList, Long userId);

	Map<Integer, String> getCommandeStatus();
	
	Map<String, String> getCommandeStatusGroup();
	
	Commande saveCommande(Commande commande);
	
	void deleteCommandeById(long idCommande);
	
	Map<Integer, String> getArticleStatus();
}
