package xiaolan.daigou.dao;

import xiaolan.daigou.domain.entity.Utilisateur;

public interface UserDao extends BaseDao<Utilisateur> {

	boolean login(Utilisateur user);
	
	Utilisateur findByUsername(String username);
}
