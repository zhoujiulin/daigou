package xiaolan.daigou.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="colis")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "idColis", scope = Colis.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@idColis", scope = Colis.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Colis implements Serializable{
	
	private static final long serialVersionUID = 2L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_colis")
    Long idColis;
    
	@Column(name="name_colis")
	String nameColis;
	
	@Column(name="commentaire_colis")
    String commentaireColis;
	
	@Column(name="status_colis")
    int statusColis;
	
	@Column(name="date_envoyer")
    Date dateEnvoyer;
	
	@Column(name="date_arriver")
    Date dateArriver;
    
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
	public int getStatusColis() {
		return statusColis;
	}
	public void setStatusColis(int statusColis) {
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
