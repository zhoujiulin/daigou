package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.domain.entity.Commande;

public interface CommandeService {

	Commande createNewCommande(Commande commande, Long userId);
	
	Commande updateCommande(Commande commande);
	
	Commande findCommandeById(Long id);
	
	List<Commande> getCommandesByStatus(List<String> statusList, Long userId);

	Map<Integer, Map<String, String>> getCommandeStatus();
	
	Map<String, String> getCommandeStatusGroup();
	
	Commande saveCommande(Commande commande);
	
	void deleteCommandeById(long idCommande);
	
	Map<Integer, Map<String, String>> getArticleStatus();
}
