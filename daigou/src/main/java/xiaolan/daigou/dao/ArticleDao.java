package xiaolan.daigou.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.Client;
import xiaolan.daigou.model.entity.Commande;

public interface ArticleDao extends BaseDao<Article> {

	List<Article> getArticleEnRoute(Long idUser);
}
