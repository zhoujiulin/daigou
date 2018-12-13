package xiaolan.daigou.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.entity.ArticleModel;
import xiaolan.daigou.entity.ArticleStockage;

@Repository
@Transactional
public class StockageDaoImpl  extends BaseDaoImpl<ArticleStockage> implements StockageDao{

	public StockageDaoImpl() {
		super(ArticleStockage.class);
	}

}
