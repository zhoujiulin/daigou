package xiaolan.daigou.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.model.entity.Article;

@Repository
@Transactional
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

	public ArticleDaoImpl() {
		super(Article.class);
	}

	@Override
	public List<Article> getArticleEnRoute(Long idUser) {
		StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(" from Article as a where a.countArticleFromStockageEnRoute > 0 and a.utilisateur.idUser = ?");
		
		Query query = em.createQuery(queryBuilder.toString(), this.clazz);
	    query.setParameter(0, idUser);
	    
		return query.getResultList();
	}
}
