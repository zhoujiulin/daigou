package xiaolan.daigou.domain;

import java.util.Date;
import java.util.Set;

import xiaolan.daigou.domain.entity.Commande;

public class LoginUserForm {

	private String username;
	private String password;
	private String confimPassword;
	private String email;
	private Date dateInscription;
	private Set<Commande> commandes;
	private String state; 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfimPassword() {
		return confimPassword;
	}
	public void setConfimPassword(String confimPassword) {
		this.confimPassword = confimPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public Set<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
