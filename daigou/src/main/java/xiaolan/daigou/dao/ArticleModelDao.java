package xiaolan.daigou.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.ArticleModel;
import xiaolan.daigou.model.entity.Client;
import xiaolan.daigou.model.entity.Commande;

public interface ArticleModelDao extends BaseDao<ArticleModel> {

}
