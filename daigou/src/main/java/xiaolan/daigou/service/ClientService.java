package xiaolan.daigou.service;

import xiaolan.daigou.entity.Client;

public interface ClientService {
	
	Client createClient(Client client);
	
	Client findClientById(Long id);
}
