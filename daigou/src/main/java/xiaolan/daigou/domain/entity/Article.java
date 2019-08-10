package xiaolan.daigou.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusArticleConverter;
import xiaolan.daigou.common.enums.EnumStatusArticle;

@Entity
@Table(name="article")
public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_article")
    private Long idArticle;
	
	@Column(name="name_article")
    private String nameArticle;
    
	@Column(name="price_achat")
    private double priceAchat;
    
	@Column(name="price_vente")
    private double priceVente;
    
	@Column(name="count")
    private int count;
    
	@Column(name="count_article_achete")
    private int countArticleAchete;
    
	@Column(name="count_article_from_stockage_france")
    private int countArticleFromStockageFrance;
    
	@Column(name="count_article_from_stockage_en_route")
    private int countArticleFromStockageEnRoute;
    
	@Column(name="count_article_from_stockage_chine")
    private int countArticleFromStockageChine;
    
	@Column(name="status_article")
	//@Enumerated(EnumType.STRING)
	@Convert(converter = StatusArticleConverter.class)
	private EnumStatusArticle statusArticle;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "colis_id")
	@JsonIgnoreProperties("articles")
	private Colis colis;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "commande_id")
	@JsonIgnoreProperties("articles")
	private Commande commande;
	
	@Column(name="date_creation")
    private Date dateCreation;
    
	@Column(name="date_envoie")
    private Date dateEnvoie;
    
	@Column(name="date_arrive")
    private Date dateArrive;
	
	@Column(name="date_distribution")
    private Date dateDistribution;
    
	@Column(name="date_termine")
    private Date dateTermine;
	
    public Article() {

    }

    public void setIdArticle(Long idArticle){
        this.idArticle = idArticle;
    }

    public Long getIdArticle(){
        return this.idArticle;
    }

    public void setNameArticle(String nameArticle){
        this.nameArticle = nameArticle;
    }

    public String getNameArticle(){
        return this.nameArticle;
    }

    public void setPriceAchat(double priceAchat){
        this.priceAchat = priceAchat;
    }

    public double getPriceAchat(){
        return this.priceAchat;
    }

    public void setPriceVente(double priceVente){
        this.priceVente = priceVente;
    }

    public double getPriceVente(){
        return this.priceVente;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public void setCountArticleAchete(int countArticleAchete){ this.countArticleAchete = countArticleAchete; }

    public int getCountArticleAchete(){
        return this.countArticleAchete;
    }

    public void setCountArticleFromStockageFrance(int countArticleFromStockageFrance){ this.countArticleFromStockageFrance = countArticleFromStockageFrance; }

    public int getCountArticleFromStockageFrance(){
        return this.countArticleFromStockageFrance;
    }

    public void setCountArticleFromStockageEnRoute(int countArticleFromStockageEnRoute){ this.countArticleFromStockageEnRoute = countArticleFromStockageEnRoute; }

    public int getCountArticleFromStockageEnRoute(){
        return this.countArticleFromStockageEnRoute;
    }

    public void setCountArticleFromStockageChine(int countArticleFromStockageChine){ this.countArticleFromStockageChine = countArticleFromStockageChine; }

    public int getCountArticleFromStockageChine(){
        return this.countArticleFromStockageChine;
    }
    
	public EnumStatusArticle getStatusArticle() {
		return statusArticle;
	}

	public void setStatusArticle(EnumStatusArticle statusArticle) {
		this.statusArticle = statusArticle;
	}
    
	public Colis getColis() {
		return colis;
	}

	public void setColis(Colis colis) {
		this.colis = colis;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
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

	public Date getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
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
}
