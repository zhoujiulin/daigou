package xiaolan.daigou.entity;

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

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name="commande")
public class Commande implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7901048489335996904L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="client_id", nullable=false)
    private Client client;
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "commande_id")
    private List<Article> articles = new ArrayList<Article>();
    
	@Column(name="date_creation")
    private Date dateCreation;
    
	@Column(name="date_envoie")
    private Date dateEnvoie;
    
	@Column(name="date_distribution")
    private Date dateDistribution;
    
	@Column(name="date_termine")
    private Date dateTermine;
    
	@Column(name="status")
    private int status;
    
	@Column(name="type_commande")
    private int typeCommande;

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

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public List<Article> getArticles(){
        return this.articles;
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

	public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){ return this.status; }

    public void setTypeCommande(int typeCommande){
        this.typeCommande = typeCommande;
    }

    public int getTypeCommande(){ return this.typeCommande; }
}
