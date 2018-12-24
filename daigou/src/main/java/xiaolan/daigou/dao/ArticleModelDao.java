package xiaolan.daigou.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleModel;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;

public interface ArticleModelDao extends BaseDao<ArticleModel> {

}
