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
	public List<Commande> getCommandesByStatus(List<String> statusList, Long userId) {
		
        StringBuilder queryBuilder = new StringBuilder();
        
        queryBuilder.append(" from Commande as c where c.utilisateur.idUser = ? and c.status in ");
        queryBuilder.append("(");
        
		for(int i=0; i<statusList.size(); i++) {
			String status = statusList.get(i);
			queryBuilder.append(Integer.valueOf(status));
			
			if(i < statusList.size() - 1) {
				queryBuilder.append(",");
			}
		}
		
        queryBuilder.append(")");
        
        Query query = em.createQuery(queryBuilder.toString(), this.clazz);
        query.setParameter(0, userId);
        
		return query.getResultList();
	}
}
