package xiaolan.daigou.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.model.entity.Client;
import xiaolan.daigou.model.entity.Commande;


public interface CommandeDao extends BaseDao<Commande>{
	
	List<Commande> getCommandesByStatus(List<String> statusList, Long userId);
	
	List<Commande> getCommandeByClient(Long idClient, Long idUser, boolean isCommandeTermineInclus);
}