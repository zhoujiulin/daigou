package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.entity.ArticleStockage;

public interface StockageService extends AbstractService<ArticleStockage> {

	List<ArticleStockage> getArticleStockages(String key, long userId);
	
	List<ArticleStockage> getAllStockageSelectable(long userId);
	
	ArticleStockage createArticleStockage(ArticleStockageDTO articleStockage, long userId);

	ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser);
	
	void updateArticleStockageForDeleteArticle(ArticleDTO articleDTO, Long idUser);
	
	List<ArticleStockageDTO> saveReinitStockage(List<ArticleStockageDTO> articleStockages);
}
