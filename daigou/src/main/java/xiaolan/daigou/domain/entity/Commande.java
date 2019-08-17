package xiaolan.daigou.domain.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusCommandeConverter;
import xiaolan.daigou.common.converter.TypeCommandeConverter;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumTypeCommande;

import java.io.Serializable;

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
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinColumn(name="client_id")
    private Client client;
    
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnoreProperties(value  = {"commande"}, allowSetters = true)
	@OrderBy("idArticle asc")
	private Set<Article> articles = new HashSet<Article>();
    
	@Column(name="status")
	@Convert(converter = StatusCommandeConverter.class)
	private EnumStatusCommande status;
    
	@Column(name="type_commande")
	@Convert(converter = TypeCommandeConverter.class)
    private EnumTypeCommande typeCommande;
	
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
    
    public void addArticle(Article article) {
    	articles.add(article);
    	article.setCommande(this);
    }
    
    public void removeArticle(Article article) {
		for (Iterator<Article> it = articles.iterator(); it.hasNext();) {
			Article a = it.next();
			
			if(a.getIdArticle().longValue() == article.getIdArticle().longValue()) {
    			a.setCommande(null);
    			a.setColis(null);
    			it.remove();
			}
		}
    }
}
