package xiaolan.daigou.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xiaolan.daigou.entity.Client;
import xiaolan.daigou.entity.Commande;


public interface CommandeDao extends BaseDao<Commande>{

	public Commande findCommandeByName();
	
	public List<Commande> getCommandesByStatus(List<Integer> statusList);
}