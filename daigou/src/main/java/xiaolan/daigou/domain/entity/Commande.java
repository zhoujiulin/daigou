package xiaolan.daigou.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import xiaolan.daigou.common.converter.StatusArticleConverter;
import xiaolan.daigou.common.converter.StatusCommandeConverter;
import xiaolan.daigou.common.enums.EnumStatusCommande;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name="commande")
public class Commande implements Serializable{

    /**
     * serialVersionUID
     */
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="client_id")
    private Client client;
    
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@OrderBy
	@JsonIgnoreProperties(value  = {"commande"}, allowSetters = true)
	private Set<Article> articles = new HashSet<Article>();
    
	@Column(name="date_creation")
    private Date dateCreation;
    
	@Column(name="date_envoie")
    private Date dateEnvoie;
    
	@Column(name="date_distribution")
    private Date dateDistribution;
    
	@Column(name="date_termine")
    private Date dateTermine;
    
	@Column(name="status")
	@Convert(converter = StatusCommandeConverter.class)
	private EnumStatusCommande status;
    
	@Column(name="type_commande")
    private int typeCommande;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utilisateur_id", nullable=false)
	private Utilisateur utilisateur;

	public Commande() {
	}
	
	public Commande(Long id) {
		this.id = id;
	}
	
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){ return this.id; }

    public void setClient(Client client){
        this.client = client;
    }

    public Client getClient(){
        return this.client;
    }
    
    public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public Date getDateDistribution() {
		return dateDistribution;
	}

	public void setDateDistribution(Date dateDistribution) {
		this.dateDistribution = dateDistribution;
	}

	public Date getDateTermine() {
		return dateTermine;
	}

	public void setDateTermine(Date dateTermine) {
		this.dateTermine = dateTermine;
	}
	
    public EnumStatusCommande getStatus() {
		return status;
	}

	public void setStatus(EnumStatusCommande status) {
		this.status = status;
	}

	public void setTypeCommande(int typeCommande){
        this.typeCommande = typeCommande;
    }

    public int getTypeCommande(){ return this.typeCommande; }

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	

    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) { 
        	return false;
        }
        
        Commande commande = (Commande) o;
        return Objects.equals(id, commande.getId());
    }
}
