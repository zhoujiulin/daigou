package xiaolan.daigou.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.model.entity.ArticleStockage;

@Repository
@Transactional
public class StockageDaoImpl  extends BaseDaoImpl<ArticleStockage> implements StockageDao{

	public StockageDaoImpl() {
		super(ArticleStockage.class);
	}

	@Override
	public ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser) {

		try {
	        String sql = " from ArticleStockage as a where a.nameArticleStockage = ? and a.utilisateur.idUser = ?";
	        Query query = em.createQuery(sql, this.clazz);
	        query.setParameter(0, nameArticleStockage);
	        query.setParameter(1, idUser);
	        
			return (ArticleStockage) query.getSingleResult();
		} catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public List<ArticleStockage> getAllStockageSelectable(long idUser) {
		try {
	        String sql = " from ArticleStockage as a where a.countStockageFranceAvailable > 0 and a.utilisateur.idUser = ?";
	        Query query = em.createQuery(sql, this.clazz);
	        query.setParameter(0, idUser);
	        
			return query.getResultList();
		} catch (NoResultException e) {
            return new ArrayList<ArticleStockage>();
        }
	}

	@Override
	public List<ArticleStockage> getArticleStockages(String key, long idUser) {
		try {
	        String sql = " from ArticleStockage as a where a.nameArticleStockage like '%" + key + "%' and a.utilisateur.idUser = ?";
	        Query query = em.createQuery(sql, this.clazz);
	        query.setParameter(0, idUser);
	        
			return query.getResultList();
		} catch (NoResultException e) {
            return new ArrayList<ArticleStockage>();
        }
	}
}
