package xiaolan.daigou.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article_stockage")
public class ArticleStockage implements Serializable{
	
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7901048489335996904L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_article_stockage")
    private Long idArticleStockage;
    
	@Column(name="name_article_stockage")
    private String nameArticleStockage;
    
	@Column(name="price_achat_stockage")
    private double priceAchatStockage;
    
	@Column(name="count_stockage_chine")
    private int countStockageChine;
    
	@Column(name="count_stokcage_france")
    private int countStockageFrance;
    
	@Column(name="count_stokcage_en_route")
    private int countStockageEnRoute;

    public ArticleStockage() {

    }

    public void setIdArticleStockage(Long idArticleStockage){ this.idArticleStockage = idArticleStockage; }

    public Long getIdArticleStockage(){
        return this.idArticleStockage;
    }

    public void setNameArticleStockage(String nameArticleStockage){ this.nameArticleStockage = nameArticleStockage; }

    public String getNameArticleStockage(){
        return this.nameArticleStockage;
    }

    public void setPriceAchatStockage(double priceAchatStockage){ this.priceAchatStockage = priceAchatStockage; }

    public double getPriceAchatStockage(){
        return this.priceAchatStockage;
    }

    public void setCountStockageChine(int countStockageChine){
        this.countStockageChine = countStockageChine;
    }

    public int getCountStockageChine(){
        return this.countStockageChine;
    }

    public void setCountStockageFrance(int countStockageFrance){
        this.countStockageFrance = countStockageFrance;
    }

    public int getCountStockageFrance(){
        return this.countStockageFrance;
    }

    public void setCountStockageEnRoute(int countStockageEnRoute){
        this.countStockageEnRoute = countStockageEnRoute;
    }

    public int getCountStockageEnRoute(){
        return this.countStockageEnRoute;
    }
}
