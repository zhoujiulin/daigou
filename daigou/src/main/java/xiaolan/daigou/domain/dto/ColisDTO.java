package xiaolan.daigou.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusColisConverter;
import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;

public class ColisDTO {

	private Long idColis;
    
	private String nameColis;
	
	private String commentaireColis;
	
	@Convert(converter = StatusColisConverter.class)
	private EnumStatusColis statusColis;
	
	private Date dateEnvoyer;
	
	private Date dateArriver;
    
	private UtilisateurDTO utilisateur;

	@JsonIgnoreProperties(value  = {"colis"}, allowSetters = true)
    private List<ArticleDTO> articles = new ArrayList<ArticleDTO>();

	public Long getIdColis() {
		return idColis;
	}

	public void setIdColis(Long idColis) {
		this.idColis = idColis;
	}

	public String getNameColis() {
		return nameColis;
	}

	public void setNameColis(String nameColis) {
		this.nameColis = nameColis;
	}

	public String getCommentaireColis() {
		return commentaireColis;
	}

	public void setCommentaireColis(String commentaireColis) {
		this.commentaireColis = commentaireColis;
	}

	public EnumStatusColis getStatusColis() {
		return statusColis;
	}

	public void setStatusColis(EnumStatusColis statusColis) {
		this.statusColis = statusColis;
	}

	public Date getDateEnvoyer() {
		return dateEnvoyer;
	}

	public void setDateEnvoyer(Date dateEnvoyer) {
		this.dateEnvoyer = dateEnvoyer;
	}

	public Date getDateArriver() {
		return dateArriver;
	}

	public void setDateArriver(Date dateArriver) {
		this.dateArriver = dateArriver;
	}

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

}
