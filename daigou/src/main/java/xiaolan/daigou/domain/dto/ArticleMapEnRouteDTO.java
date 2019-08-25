package xiaolan.daigou.domain.dto;

import java.util.List;

import xiaolan.daigou.domain.entity.Article;

public class ArticleMapEnRouteDTO {
    Long idArticle;
    List<ArticleDTO> articles;
    
	public Long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	public List<ArticleDTO> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
}
