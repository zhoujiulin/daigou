package xiaolan.daigou.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;

public interface ClientDao extends BaseDao<Client> {

	Client findClientByName(Client client, Long userId);
	
	Client findClientByName(String nameWechat, String nameLivraison, Long userId);
}
