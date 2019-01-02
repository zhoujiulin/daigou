package xiaolan.daigou.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;


public interface CommandeDao extends BaseDao<Commande>{
	
	public List<Commande> getCommandesByStatus(List<String> statusList, Long userId);
}