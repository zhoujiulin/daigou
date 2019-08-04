package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.entity.ArticleStockage;

public interface StockageService {

	List<ArticleStockage> getAllStockage(long userId);
	
	ArticleStockage createArticleStockage(ArticleStockage articleStockage, long userId);
	
	ArticleStockage saveArticleStokage(ArticleStockage articleStockage);
	
	ArticleStockage findById(Long id);
}
