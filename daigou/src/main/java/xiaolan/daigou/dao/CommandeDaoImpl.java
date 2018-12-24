package xiaolan.daigou.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.domain.entity.Commande;

@Repository
@Transactional
public class CommandeDaoImpl extends BaseDaoImpl<Commande> implements CommandeDao{
	
	public CommandeDaoImpl() {
		super(Commande.class);
	}

	@Override
	public Commande findCommandeByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getCommandesByStatus(List<Integer> statusList) {
		
        StringBuilder queryBuilder = new StringBuilder();
        
        queryBuilder.append(" from Commande c where c.status in ");
        queryBuilder.append("(");
        
		for(int i=0; i<statusList.size(); i++) {
			queryBuilder.append(statusList.get(i));
			
			if(i < statusList.size() - 1) {
				queryBuilder.append(",");
			}
		}
		
        queryBuilder.append(")");
        
        Query query = em.createQuery(queryBuilder.toString(), this.clazz);
        
		return query.getResultList();
	}
}
