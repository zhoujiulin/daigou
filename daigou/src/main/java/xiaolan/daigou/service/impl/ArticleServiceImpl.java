package xiaolan.daigou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.service.ArticleService;

@Service
public class ArticleServiceImpl extends AbstractServiceImpl<Article> implements ArticleService{

    public ArticleServiceImpl() {
        super(Article.class);
    }
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public BaseDao<Article> getDao() {
		return this.articleDao;
	}
}
