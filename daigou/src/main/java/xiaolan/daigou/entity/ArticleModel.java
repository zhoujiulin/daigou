package xiaolan.daigou.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article_model")
public class ArticleModel implements Serializable{
	
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7901048489335996904L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_article_model")
    private Long idArticleModel;
    
	@Column(name="name_article_model")
    private String nameArticleModel;
    
	@Column(name="price_achete_model")
    private double priceAcheteModel;
    
	@Column(name="price_vente_model")
    private double priceVenteModel;

    public void setIdArticleModel(Long idArticleModel){ this.idArticleModel = idArticleModel; }

    public Long getIdArticleModel(){ return this.idArticleModel; }

    public void setNameArticleModel(String nameArticleModel){ this.nameArticleModel = nameArticleModel; }

    public String getNameArticleModel(){ return this.nameArticleModel; }

    public void setPriceAcheteModel(double priceAcheteModel){ this.priceAcheteModel = priceAcheteModel; }

    public double getPriceAcheteModel(){
        return this.priceAcheteModel;
    }

    public void setPriceVenteModel(double priceVenteModel){ this.priceVenteModel = priceVenteModel; }

    public double getPriceVenteModel(){
        return this.priceVenteModel;
    }
}
