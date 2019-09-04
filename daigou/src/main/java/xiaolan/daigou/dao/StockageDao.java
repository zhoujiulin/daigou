package xiaolan.daigou.dao;

import java.util.List;

import xiaolan.daigou.model.entity.ArticleStockage;
import xiaolan.daigou.model.entity.Utilisateur;

public interface StockageDao extends BaseDao<ArticleStockage> {

	ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser);
	
	List<ArticleStockage> getAllStockageSelectable(long idUser);
	
	List<ArticleStockage> getArticleStockages(String key, long idUser);
}
