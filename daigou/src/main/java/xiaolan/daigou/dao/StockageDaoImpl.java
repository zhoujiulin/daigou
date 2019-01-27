package xiaolan.daigou.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.domain.entity.ArticleModel;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Utilisateur;

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
}
