package xiaolan.daigou.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.domain.entity.Client;

@Repository
@Transactional
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao{

	public ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public Client findClientByName(Client client, Long userId) {

		return findClientByName(client.getNameWechat(), client.getNameLivraison(), userId);
	}

	@Override
	public Client findClientByName(String nameWechat, String nameLivraison, Long userId) {
        Query query = em.createQuery(" from Client c where c.nameWechat = ? and c.nameLivraison = ? and c.utilisateur.idUser = ?", this.clazz);
        query.setParameter(0, nameWechat);
        query.setParameter(1, nameLivraison);
        query.setParameter(2, userId);
        
		return (Client) query.getResultList().stream().findFirst().orElse(null);
	}
}
