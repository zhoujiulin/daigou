package xiaolan.daigou.service;

import xiaolan.daigou.domain.dto.ArticleInClientDTO;
import xiaolan.daigou.domain.entity.Article;

public interface ArticleService extends AbstractService<Article>{
	
	void terminerArticle(ArticleInClientDTO articleInClientDTO, Long idUser);
}
