package xiaolan.daigou.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.Utilisateur;

@Repository
@Transactional
public class UtilisateurDaoImpl extends BaseDaoImpl<Utilisateur> implements UtilisateurDao{

	public UtilisateurDaoImpl() {
		super(Utilisateur.class);
	}

	@Override
	public Utilisateur findByUsername(String username) {

        String sql = " from Utilisateur u where u.username='" + username + "'";
        Query query = em.createQuery(sql, this.clazz);
        
		return (Utilisateur) query.getSingleResult();
	}
}
