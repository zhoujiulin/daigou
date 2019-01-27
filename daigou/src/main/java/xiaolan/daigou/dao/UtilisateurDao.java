package xiaolan.daigou.dao;

import xiaolan.daigou.domain.entity.Utilisateur;

public interface UtilisateurDao extends BaseDao<Utilisateur> {
	
	Utilisateur findByUsername(String username);
}
