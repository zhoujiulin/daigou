package xiaolan.daigou.web.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.UserService;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService{

	@Autowired 
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = userService.findByUsername(username);
		if(utilisateur == null) {
			throw new UsernameNotFoundException(String.format("No User found with username '%s'", username));
		}
		return JwtuserFactory.create(utilisateur);
	}
}
