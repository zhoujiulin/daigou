package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.utils.DozerUtils;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.domain.dto.ClientDTO;
import xiaolan.daigou.domain.dto.CommandeDTO;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.service.ClientService;

@Service
public class ClientServiceImpl extends AbstractServiceImpl<Client> implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private CommandeDao commandeDao;
	
    @Autowired
    protected Mapper dozerMapper;
	
    public ClientServiceImpl() {
        super(Client.class);
    }

	@Override
	public List<ClientDTO> getClientHasCommandeEnCours(Long idUser) {
		List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
		List<Client> clients = this.clientDao.getAll(idUser);
		
		for(Client client : clients) {
			List<Commande> commandes = this.getCommandeByClient(client.getId(), idUser);
			List<CommandeDTO> commandeDTOs = DozerUtils.mapList(dozerMapper, commandes, CommandeDTO.class);
			
			ClientDTO clientDTO = dozerMapper.map(client, ClientDTO.class);
			clientDTO.setCommandes(commandeDTOs);
			clientDTOs.add(clientDTO);
		}
		
		return clientDTOs;
	}
	
	@Override
	public List<Commande> getCommandeByClient(Long idClient, Long idUser) {
		return this.commandeDao.getCommandeByClient(idClient, idUser);
	}

	@Override
	public BaseDao<Client> getDao() {
		return this.clientDao;
	}
}
