package xiaolan.daigou.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.ColisDao;
import xiaolan.daigou.model.entity.Colis;
import xiaolan.daigou.model.enums.EnumStatusColis;

@Repository
@Transactional
public class ColisDaoImpl extends BaseDaoImpl<Colis> implements ColisDao {

	public ColisDaoImpl() {
		super(Colis.class);
	}

	@Override
	public List<Colis> getColisByStatus(int status, Long idUser) {
        StringBuilder queryBuilder = new StringBuilder();
        
        queryBuilder.append(" from Colis as c where c.utilisateur.idUser = ? and c.statusColis = ?");
           
        Query query = em.createQuery(queryBuilder.toString(), this.clazz);
        query.setParameter(0, idUser);
        query.setParameter(1, EnumStatusColis.getValueByIndex(status));
        
		return query.getResultList();
	}

	@Override
	public Colis getLastColis(Long idUser) {
		Colis last = null;
		try {
			Query query = em.createQuery("from Colis as c where c.utilisateur.idUser = ? order by c.idColis DESC");
			query.setParameter(0, idUser);
			query.setMaxResults(1);
			last = (Colis) query.getSingleResult();
		} catch (Exception e) {
			last = null;
		}	
		return last;
	}
}
