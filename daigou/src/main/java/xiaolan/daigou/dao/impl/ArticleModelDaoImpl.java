package xiaolan.daigou.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.ArticleModelDao;
import xiaolan.daigou.domain.entity.ArticleModel;

@Repository
@Transactional
public class ArticleModelDaoImpl extends BaseDaoImpl<ArticleModel> implements ArticleModelDao{

	public ArticleModelDaoImpl() {
		super(ArticleModel.class);
	}

}
