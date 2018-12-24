package xiaolan.daigou.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.domain.entity.Utilisateur;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<Utilisateur> implements UserDao{

	public UserDaoImpl() {
		super(Utilisateur.class);
	}

	@Override
	public boolean login(Utilisateur user) {

		return false;
	}

	@Override
	public Utilisateur findByUsername(String username) {

        String sql = " from Utilisateur u where u.username='" + username + "'";
        Query query = em.createQuery(sql, this.clazz);
        
		return (Utilisateur) query.getResultList().get(0);
	}
}
