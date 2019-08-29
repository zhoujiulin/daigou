package xiaolan.daigou.dao;

import xiaolan.daigou.model.entity.Utilisateur;

public interface UtilisateurDao extends BaseDao<Utilisateur> {
	
	Utilisateur findByUsername(String username);
}
