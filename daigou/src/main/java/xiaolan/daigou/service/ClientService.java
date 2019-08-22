package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.dto.ClientDTO;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;

public interface ClientService extends AbstractService<Client>{

	List<ClientDTO> getClientHasCommandeEnCours(Long idUser);
	
	List<Commande> getCommandeByClient(Long idClient, Long idUser);
}
