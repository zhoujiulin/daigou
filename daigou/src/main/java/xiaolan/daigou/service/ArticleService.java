package xiaolan.daigou.service;

import xiaolan.daigou.domain.entity.Article;

public interface ArticleService {
	
	Article createArticle(Article article);
	
	Article updateArticle(Article article);
	
	Article findArticleById(Long id);
}
