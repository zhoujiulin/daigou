package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.domain.dto.ArticleDTO;
import xiaolan.daigou.domain.dto.ArticleInClientDTO;
import xiaolan.daigou.domain.dto.ColisDTO;
import xiaolan.daigou.domain.entity.Article;

public interface ArticleService extends AbstractService<Article>{
	
	void terminerArticle(ArticleInClientDTO articleInClientDTO, Long idUser);
	
	Map<Long, List<ArticleDTO>> computeArticleStockageFromColiAarriver(ColisDTO colisDTO, Long idUser);
	
	List<Article> getArticleEnRoute(Long idUser);
}
