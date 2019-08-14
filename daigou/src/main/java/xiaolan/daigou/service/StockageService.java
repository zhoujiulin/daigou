package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.entity.ArticleStockage;

public interface StockageService extends AbstractService<ArticleStockage> {

	List<ArticleStockage> getAllStockage(long userId);
	
	ArticleStockage createArticleStockage(ArticleStockage articleStockage, long userId);
	
	ArticleStockage saveArticleStokage(ArticleStockage articleStockage);

	ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser);
}
