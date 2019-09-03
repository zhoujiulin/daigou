package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.model.dto.ClientDTO;
import xiaolan.daigou.model.entity.Client;
import xiaolan.daigou.model.entity.Commande;

public interface ClientService extends AbstractService<Client>{

	List<ClientDTO> getClientHasCommandeEnCours(Long idUser);
	
	List<Commande> getCommandeByClient(Long idClient, Long idUser, boolean isCommandeTermineInclus);
}
