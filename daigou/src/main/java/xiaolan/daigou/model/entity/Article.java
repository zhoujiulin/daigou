package xiaolan.daigou.model.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusArticleConverter;
import xiaolan.daigou.common.converter.StatusArticleAcheteDistribueConverter;
import xiaolan.daigou.common.converter.StatusArticlePreparationConverter;
import xiaolan.daigou.common.converter.StatusArticleStockageChineDistribueConverter;
import xiaolan.daigou.common.converter.TypeArticleConverter;
import xiaolan.daigou.model.enums.EnumStatusArticle;
import xiaolan.daigou.model.enums.EnumStatusArticleAcheteDistribue;
import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.model.enums.EnumStatusArticleStockageChineDistribue;
import xiaolan.daigou.model.enums.EnumTypeArticle;

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
	
	@Column(name="count_article_achete_distribue")
    private int countArticleAcheteDistribue;
    
	@Column(name="count_article_from_stockage_france")
    private int countArticleFromStockageFrance;
    
	@Column(name="count_article_from_stockage_en_route")
    private int countArticleFromStockageEnRoute;
    
	@Column(name="count_article_from_stockage_chine")
    private int countArticleFromStockageChine;
	
	@Column(name="count_article_from_stockage_chine_distribue")
    private int countArticleFromStockageChineDistribue;
    
	@Column(name="status_preparation")
	//@Enumerated(EnumType.STRING)
	@Convert(converter = StatusArticlePreparationConverter.class)
	private EnumStatusArticlePreparation statusArticlePreparation;
	
	@Column(name="status_article")
	@Convert(converter = StatusArticleConverter.class)
	private EnumStatusArticle statusArticle;
	
	@Column(name="status_article_achete_distribue")
	@Convert(converter = StatusArticleAcheteDistribueConverter.class)
	private EnumStatusArticleAcheteDistribue statusArticleAcheteDistribue;
	
	@Column(name="status_article_stockage_chine_distribue")
	@Convert(converter = StatusArticleStockageChineDistribueConverter.class)
	private EnumStatusArticleStockageChineDistribue statusArticleStockageChineDistribue;
	
	@Column(name="type_article")
	@Convert(converter = TypeArticleConverter.class)
	private EnumTypeArticle typeArticle;
	
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utilisateur_id", nullable=false)
	private Utilisateur utilisateur;
	
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
    
	public EnumStatusArticlePreparation getStatusArticlePreparation() {
		return statusArticlePreparation;
	}

	public void setStatusArticlePreparation(EnumStatusArticlePreparation statusArticlePreparation) {
		this.statusArticlePreparation = statusArticlePreparation;
	}
	
	public EnumStatusArticle getStatusArticle() {
		return statusArticle;
	}

	public void setStatusArticle(EnumStatusArticle statusArticle) {
		this.statusArticle = statusArticle;
	}

	public EnumStatusArticleAcheteDistribue getStatusArticleAcheteDistribue() {
		return statusArticleAcheteDistribue;
	}

	public void setStatusArticleAcheteDistribue(EnumStatusArticleAcheteDistribue statusArticleAcheteDistribue) {
		this.statusArticleAcheteDistribue = statusArticleAcheteDistribue;
	}

	public EnumStatusArticleStockageChineDistribue getStatusArticleStockageChineDistribue() {
		return statusArticleStockageChineDistribue;
	}

	public void setStatusArticleStockageChineDistribue(
			EnumStatusArticleStockageChineDistribue statusArticleStockageChineDistribue) {
		this.statusArticleStockageChineDistribue = statusArticleStockageChineDistribue;
	}

	public EnumTypeArticle getTypeArticle() {
		return typeArticle;
	}

	public void setTypeArticle(EnumTypeArticle typeArticle) {
		this.typeArticle = typeArticle;
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

	public int getCountArticleAcheteDistribue() {
		return countArticleAcheteDistribue;
	}

	public void setCountArticleAcheteDistribue(int countArticleAcheteDistribue) {
		this.countArticleAcheteDistribue = countArticleAcheteDistribue;
	}

	public int getCountArticleFromStockageChineDistribue() {
		return countArticleFromStockageChineDistribue;
	}

	public void setCountArticleFromStockageChineDistribue(int countArticleFromStockageChineDistribue) {
		this.countArticleFromStockageChineDistribue = countArticleFromStockageChineDistribue;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
