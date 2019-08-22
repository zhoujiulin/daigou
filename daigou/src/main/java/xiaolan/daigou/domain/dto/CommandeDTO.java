package xiaolan.daigou.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusCommandeConverter;
import xiaolan.daigou.common.converter.TypeCommandeConverter;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;

public class CommandeDTO {

    private Long id;
	
    private ClientDTO client;
    
	@JsonIgnoreProperties(value  = {"commande"}, allowSetters = true)
	private Set<ArticleDTO> articles = new HashSet<ArticleDTO>();
    
	@Convert(converter = StatusCommandeConverter.class)
	private EnumStatusCommande status;
    
	@Convert(converter = TypeCommandeConverter.class)
    private EnumTypeCommande typeCommande;
	
	private UtilisateurDTO utilisateur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public Set<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleDTO> articles) {
		this.articles = articles;
	}

	public EnumStatusCommande getStatus() {
		return status;
	}

	public void setStatus(EnumStatusCommande status) {
		this.status = status;
	}

	public EnumTypeCommande getTypeCommande() {
		return typeCommande;
	}

	public void setTypeCommande(EnumTypeCommande typeCommande) {
		this.typeCommande = typeCommande;
	}

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}
}
