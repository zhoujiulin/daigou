package xiaolan.daigou.web.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import xiaolan.daigou.model.entity.Utilisateur;

public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754590875282201303L;

	private final Long id;
	private final String username;
	private final String password;
	private final Utilisateur utilisateur;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	public JwtUser(Long id, String username, String password, Utilisateur utilisateur,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.utilisateur = utilisateur;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public Utilisateur getUser() {
		return utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
