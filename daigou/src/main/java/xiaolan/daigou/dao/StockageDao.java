package xiaolan.daigou.dao;

import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Utilisateur;

public interface StockageDao extends BaseDao<ArticleStockage> {

	public ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser);
}
