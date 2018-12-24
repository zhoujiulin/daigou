package xiaolan.daigou.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;

@Repository
@Transactional
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao{

	public ClientDaoImpl() {
		super(Client.class);
	}

}
