package xiaolan.daigou.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.domain.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public Client createClient(Client client) {
		
		return this.clientDao.save(client);
	}

	@Override
	public Client findClientById(Long id) {
		
		Client client = this.clientDao.findById(id);
		
		return client;
	}

}
