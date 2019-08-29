package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleInClientDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.model.entity.Article;

public interface ArticleService extends AbstractService<Article>{
	
	void envoyerArticleAuClient(ArticleInClientDTO articleInClientDTO, Long idUser);
	
	Map<Long, List<ArticleDTO>> computeArticleStockageFromColiAarriver(ColisDTO colisDTO, Long idUser);
	
	List<Article> getArticleEnRoute(Long idUser);
}
