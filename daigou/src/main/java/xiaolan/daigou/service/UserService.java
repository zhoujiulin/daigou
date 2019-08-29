package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.model.LoginUserForm;
import xiaolan.daigou.model.entity.Utilisateur;

public interface UserService {

	boolean login(LoginUserForm user);
	
	void inscription(LoginUserForm user);
	
    Utilisateur findByUsername(String username);
    
    Utilisateur inscription(Utilisateur utilisateur);

	List<Utilisateur> findAll();
}
