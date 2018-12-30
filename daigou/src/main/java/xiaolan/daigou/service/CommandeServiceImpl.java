package xiaolan.daigou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.dao.ClientDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;

@Service
public class CommandeServiceImpl implements CommandeService{

	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private ClientDao clientDao;

	@Override
	public Commande createNewCommande(Commande commande) {
		Client client = clientDao.findClientByName(commande.getClient());
		if(client != null) {
			commande.setClient(client);
		}
		
		commande.setDateCreation(new Date());
		commande.setStatus(EnumStatusCommande.NEW_COMMANDE.getIndex());
		
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public Commande updateCommande(Commande commande) {
		Commande c= this.commandeDao.save(commande);
		return c;
	}

	@Override
	public List<Commande> findAll() {
		
		return this.commandeDao.getAll();
	}

	@Override
	public Commande findCommandeById(Long id) {

		return this.commandeDao.findById(id);
	}

	@Override
	public List<Commande> getCommandesByStatus(List<String> statusList) {
		List<Commande> commandes = this.commandeDao.getCommandesByStatus(statusList);
		return commandes;
	}

	@Override
	public Map<Integer, String> getCommandeStatus() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		EnumStatusCommande[] statusList = EnumStatusCommande.values();
		
		for(EnumStatusCommande status : statusList) {
			map.put(status.getIndex(), status.getValue());
		}
		return map;
	}
	
	@Override
	public Map<String, String> getCommandeStatusGroup() {
		Map<String, String> map = new HashMap<String, String>();
		EnumStatusCommande[] statusList = EnumStatusCommande.values();
		
		EnumStatusCommandeGroup[] statusGroupList = EnumStatusCommandeGroup.values();
		for(EnumStatusCommandeGroup group : statusGroupList) {
			StringBuilder sb = new StringBuilder();
			
			for(EnumStatusCommande status : statusList) {
				if(status.isInGroup(group)) {
					sb.append(status.getIndex());
					sb.append(",");
				}
			}
			
			String text = sb.toString().substring(0, sb.length() - 1);
			map.put(text, group.getValue());
		}
		return map;
	}

	@Override
	public Commande saveCommande(Commande commande) {
		
		return this.commandeDao.save(commande);
	}

	@Override
	public void deleteCommandeById(long idCommande) {
		this.commandeDao.deleteById(idCommande);
	}
}
