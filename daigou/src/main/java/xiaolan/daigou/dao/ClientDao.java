package xiaolan.daigou.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Client;

public interface ClientDao extends BaseDao<Client> {

	Client findClientByName(Client client, Long userId);
	
	Client findClientByName(String nameWechat, String nameLivraison, Long userId);
}
