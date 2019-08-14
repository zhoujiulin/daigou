package xiaolan.daigou.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusColisConverter;
import xiaolan.daigou.common.enums.EnumStatusColis;

@Entity
@Table(name="colis")
public class Colis implements Serializable{
	
	private static final long serialVersionUID = 2L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_colis")
	private Long idColis;
    
	@Column(name="name_colis")
	private String nameColis;
	
	@Column(name="commentaire_colis")
	private String commentaireColis;
	
	@Column(name="status_colis")
	@Convert(converter = StatusColisConverter.class)
	private EnumStatusColis statusColis;
	
	@Column(name="date_envoyer")
	private Date dateEnvoyer;
	
	@Column(name="date_arriver")
	private Date dateArriver;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utilisateur_id", nullable=false)
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "colis", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value  = {"colis"}, allowSetters = true)
    private List<Article> articles = new ArrayList<Article>();
	
	public Colis() {

	}
	
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
	public Date getDateEnvoyer() {
		return dateEnvoyer;
	}
	public EnumStatusColis getStatusColis() {
		return statusColis;
	}
	public void setStatusColis(EnumStatusColis statusColis) {
		this.statusColis = statusColis;
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
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
