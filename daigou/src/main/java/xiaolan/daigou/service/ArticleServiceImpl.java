package xiaolan.daigou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.domain.entity.Article;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public Article createArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleDao.save(article);
	}

	@Override
	public Article updateArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleDao.save(article);
	}

	@Override
	public Article findArticleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
