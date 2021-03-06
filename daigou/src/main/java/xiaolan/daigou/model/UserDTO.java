package xiaolan.daigou.model;

import java.io.Serializable;

import xiaolan.daigou.model.entity.Utilisateur;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6651130791664508666L;
	
	private Utilisateur utilisateur;
	private String token;
	
	public UserDTO(Utilisateur utilisateur, String token) {
		super();
		this.utilisateur = utilisateur;
		this.token = token;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
