package xiaolan.daigou.model.dto;

import java.util.ArrayList;
import java.util.List;

import xiaolan.daigou.model.entity.Utilisateur;

public class ClientDTO {

    private Long id;
	
	private String nameWechat;
	
	private String nameLivraison;
	
	private String telephone;
	
	private String adresse;
	
	private Utilisateur utilisateur;
	
	private List<CommandeDTO> commandes = new ArrayList<CommandeDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameWechat() {
		return nameWechat;
	}

	public void setNameWechat(String nameWechat) {
		this.nameWechat = nameWechat;
	}

	public String getNameLivraison() {
		return nameLivraison;
	}

	public void setNameLivraison(String nameLivraison) {
		this.nameLivraison = nameLivraison;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<CommandeDTO> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<CommandeDTO> commandes) {
		this.commandes = commandes;
	} 
}
