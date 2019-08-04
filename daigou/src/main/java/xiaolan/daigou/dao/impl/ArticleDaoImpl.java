package xiaolan.daigou.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.domain.entity.Article;

@Repository
@Transactional
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

	public ArticleDaoImpl() {
		super(Article.class);
	}

}
