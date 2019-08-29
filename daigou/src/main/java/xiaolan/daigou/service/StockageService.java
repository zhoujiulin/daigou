package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.entity.ArticleStockage;

public interface StockageService extends AbstractService<ArticleStockage> {

	List<ArticleStockage> getAllStockage(long userId);
	
	List<ArticleStockage> getAllStockageSelectable(long userId);
	
	ArticleStockage createArticleStockage(ArticleStockage articleStockage, long userId);
	
	ArticleStockage saveArticleStokage(ArticleStockage articleStockage);

	ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser);
	
	void updateArticleStockageForDeleteArticle(ArticleDTO articleDTO, Long idUser);
}
