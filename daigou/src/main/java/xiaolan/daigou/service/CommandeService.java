package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.domain.entity.Commande;

public interface CommandeService {

	Commande createNewCommande(Commande commande, Long userId);
	
	Commande findCommandeById(Long id);
	
	List<Commande> getCommandesByStatus(List<String> statusList, Long userId);

	Map<Integer, Map<String, String>> getCommandeStatus();
	
	Map<String, String> getCommandeStatusGroup();
	
	Commande saveCommande(Commande commande);
	
	void deleteCommandeById(long idCommande);
}
