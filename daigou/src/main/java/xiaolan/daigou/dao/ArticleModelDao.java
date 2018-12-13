package xiaolan.daigou.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.entity.Article;
import xiaolan.daigou.entity.ArticleModel;
import xiaolan.daigou.entity.Client;
import xiaolan.daigou.entity.Commande;

public interface ArticleModelDao extends BaseDao<ArticleModel> {

}
