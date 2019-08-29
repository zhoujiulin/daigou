package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticleDeserializer;

@JsonDeserialize(using = StatusArticleDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticle {
	ARTICLE_NON_ENVOYE(1, "enum.article.status.articleNonEnvoye", DaigouConstant.CSS_RED),
	ARTICLE_ENVOYE_SUR_LA_ROUTE(2, "enum.article.status.articleEnvoyeSurLaRoute", DaigouConstant.CSS_VERT),
	ARTICLE_ARRIVE_EN_CHINE_MANQUE_INFO_CLIENT(3, "enum.article.status.articleArriveEnChine.manqueInfoClient", DaigouConstant.CSS_VERT),
	ARTICLE_ARRIVE_EN_CHINE_PRET_A_DISTRIBUER(4, "enum.article.status.articleArriveEnChine.pretADistribuer", DaigouConstant.CSS_VERT),
	ARTICLE_ENVOYE_AU_CLIENT(5, "enum.article.status.articleEnvoyeAuClient", DaigouConstant.CSS_VERT),
	ARTICLE_TERMINE(6, "enum.article.status.articleTermine", DaigouConstant.CSS_VERT);
    
	private int index;
    private String value;
    private String color;
    
    private EnumStatusArticle(int index, String value, String color) {
    	this.index = index;
    	this.value = value;
    	this.color = color;
    }
    
    public int getIndex() {
    	return this.index;
    }
    
    public String getValue() {
    	return this.value;
    }
    
    public String getColor() {
    	return this.color;
    }
    
    public static EnumStatusArticle getValueByIndex(int index) {
    	EnumStatusArticle status = null;
        for(EnumStatusArticle s : values()) {
            if(s.getIndex() == index) {
                status = s;
            }
        }
		return status;
    }
    
    public static EnumStatusArticle getEnumByIndex(int index) {
        for(EnumStatusArticle status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
