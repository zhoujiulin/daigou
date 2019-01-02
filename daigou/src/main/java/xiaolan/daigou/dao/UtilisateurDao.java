package xiaolan.daigou.dao;

import xiaolan.daigou.domain.entity.Utilisateur;

public interface UtilisateurDao extends BaseDao<Utilisateur> {

	boolean login(Utilisateur user);
	
	Utilisateur findByUsername(String username);
}
