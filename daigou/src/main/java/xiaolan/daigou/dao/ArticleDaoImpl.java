package xiaolan.daigou.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.entity.Article;

@Repository
@Transactional
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

	public ArticleDaoImpl() {
		super(Article.class);
	}

}
