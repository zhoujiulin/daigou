package xiaolan.daigou.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.entity.ArticleModel;

@Repository
@Transactional
public class ArticleModelDaoImpl extends BaseDaoImpl<ArticleModel> implements ArticleModelDao{

	public ArticleModelDaoImpl() {
		super(ArticleModel.class);
	}

}
