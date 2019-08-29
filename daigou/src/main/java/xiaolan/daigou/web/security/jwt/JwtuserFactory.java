package xiaolan.daigou.web.security.jwt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import xiaolan.daigou.model.entity.Utilisateur;

public class JwtuserFactory {

	public static JwtUser create(Utilisateur utilisateur) {
		return new JwtUser(utilisateur.getIdUser(), 
				utilisateur.getUsername(), 
				utilisateur.getPassword(), 
				utilisateur,
				maptoGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE " + utilisateur.getRole()))), 
				utilisateur.isEnabled());
	}

	private static List<GrantedAuthority> maptoGrantedAuthorities(List<String> authorites) {
		
		return authorites.stream()
				.map(Authority -> new SimpleGrantedAuthority(Authority))
				.collect(Collectors.toList());
	}
}
