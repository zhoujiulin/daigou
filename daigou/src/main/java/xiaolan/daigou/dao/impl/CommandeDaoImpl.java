package xiaolan.daigou.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.model.entity.Commande;
import xiaolan.daigou.model.enums.EnumStatusCommande;

@Repository
@Transactional
public class CommandeDaoImpl extends BaseDaoImpl<Commande> implements CommandeDao{
	
	public CommandeDaoImpl() {
		super(Commande.class);
	}

	@Override
	public List<Commande> getCommandesByStatus(List<String> statusList, Long userId) {
		
        StringBuilder queryBuilder = new StringBuilder();
        
        queryBuilder.append(" from Commande as c where c.utilisateur.idUser = ?");
        queryBuilder.append(" and (");
        for(int i = 0; i < statusList.size(); i++) {
        	queryBuilder.append("c.status = " + statusList.get(i));
        	
			if(i < statusList.size() - 1) {
				queryBuilder.append(" or ");
			}
        }
        queryBuilder.append(") ");
        queryBuilder.append(" ORDER BY c.id ASC");
        
//      queryBuilder.append(" from Commande as c where c.utilisateur.idUser = ? and c.status in ");
//      queryBuilder.append("(");    
//		for(int i=0; i<statusList.size(); i++) {
//			String status = statusList.get(i);
//			queryBuilder.append(status);
//			
//			if(i < statusList.size() - 1) {
//				queryBuilder.append(",");
//			}
//		}
//        queryBuilder.append(")");
        
        Query query = em.createQuery(queryBuilder.toString(), this.clazz);
        query.setParameter(0, userId);

		return query.getResultList();
	}

	@Override
	public List<Commande> getCommandeByClient(Long idClient, Long idUser, boolean isCommandeTermineInclus) {
		StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(" from Commande as c where c.client.id = ? and c.utilisateur.idUser = ?");
        
        if(isCommandeTermineInclus) {
        	queryBuilder.append(" and c.status != " + EnumStatusCommande.TERMINE.getIndex());
        }
		
		Query query = em.createQuery(queryBuilder.toString(), this.clazz);
	    query.setParameter(0, idClient);
	    query.setParameter(1, idUser);
	    
		return query.getResultList();
	}
}
