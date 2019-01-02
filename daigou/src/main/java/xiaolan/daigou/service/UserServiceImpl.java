package xiaolan.daigou.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.utils.PasswordUtil;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.LoginUserForm;
import xiaolan.daigou.domain.entity.Utilisateur;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UtilisateurDao userDao;
	
	@Override
	public boolean login(LoginUserForm user) {

		return true;
	}

	@Override
	public void inscription(LoginUserForm user) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUsername(user.getUsername());
		utilisateur.setPassword(user.getPassword());
		
		this.userDao.save(utilisateur);
	}
	
	@Override
	public Utilisateur findByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	@Override
	public Utilisateur inscription(Utilisateur utilisateur) {
		String password = PasswordUtil.getPasswordHash(utilisateur.getPassword());
		utilisateur.setPassword(password);
		utilisateur.setCreatedDate(new Date());
		
		return userDao.save(utilisateur);
	}

	@Override
	public List<Utilisateur> findAll() {
		return this.userDao.getAll();
	}
}
