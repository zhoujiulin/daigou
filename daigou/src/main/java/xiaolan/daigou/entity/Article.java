package xiaolan.daigou.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article implements Serializable{
	
    private static final long serialVersionUID = 7901048489335996904L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_article")
    private Long idArticle;
	
	@Column(name="name_article")
    private String nameArticle;
    
	@Column(name="price_achat")
    private double priceAchat;
    
	@Column(name="name_vente")
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
    private int statusArticle;

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

    public void setStatusArticle(int statusArticle){
        this.statusArticle = statusArticle;
    }

    public int getStatusArticle(){
        return this.statusArticle;
    }
}
