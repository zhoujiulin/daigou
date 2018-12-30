package xiaolan.daigou.dao;

import javax.persistence.Query;

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

	@Override
	public Client findClientByName(Client client) {

		return findClientByName(client.getNameWechat(), client.getNameLivraison());
	}

	@Override
	public Client findClientByName(String nameWechat, String nameLivraison) {
        Query query = em.createQuery(" from Client c where c.nameWechat = ? and c.nameLivraison = ?", this.clazz);
        query.setParameter(0, nameWechat);
        query.setParameter(1, nameLivraison);
        
		return (Client) query.getResultList().stream().findFirst().orElse(null);
	}
}
