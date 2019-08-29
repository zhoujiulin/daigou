package xiaolan.daigou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.common.utils.PasswordUtil;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.model.LoginUserForm;
import xiaolan.daigou.model.entity.Commande;
import xiaolan.daigou.model.entity.Utilisateur;
import xiaolan.daigou.model.exception.DaigouException;
import xiaolan.daigou.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UtilisateurDao userDao;
	
	@Override
	public boolean login(LoginUserForm user) {

		return true;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public void inscription(LoginUserForm user) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUsername(user.getUsername());
		utilisateur.setPassword(user.getPassword());
		
		this.userDao.save(utilisateur);
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public Utilisateur findByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public Utilisateur inscription(Utilisateur utilisateur) {
		String password = PasswordUtil.getPasswordHash(utilisateur.getPassword());
		utilisateur.setPassword(password);
		utilisateur.setCreatedDate(new Date());
		
		return userDao.save(utilisateur);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public List<Utilisateur> findAll() {
		return this.userDao.getAll();
	}
}
