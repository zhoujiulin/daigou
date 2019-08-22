package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.common.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;

public interface CommandeService extends AbstractService<Commande> {

	Commande createNewCommande(Commande commande, Long userId);
	
	List<Commande> getCommandesByStatus(List<String> statusList, Long userId);

	Map<Integer, Map<String, String>> getCommandeStatus();
	
	Map<String, String> getCommandeStatusGroup();
	
	Commande updateCommande(Commande commande);
	
	void deleteCommandeById(Long id);
}
