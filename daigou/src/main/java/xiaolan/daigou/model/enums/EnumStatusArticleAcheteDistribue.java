package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticleAcheteDistribueDeserializer;

@JsonDeserialize(using = StatusArticleAcheteDistribueDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticleAcheteDistribue {
	ARTICLE_ACHETE_NON_DISTRIBUE(1, "enum.article.statusDistribue.articleAcheteNonDistribue", DaigouConstant.CSS_ORANGE),
	ARTICLE_ACHETE_DISTRIBUE(2, "enum.article.statusDistribue.articleAcheteDistribue", DaigouConstant.CSS_VERT);
    
	private int index;
    private String value;
    private String color;
    
    private EnumStatusArticleAcheteDistribue(int index, String value, String color) {
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
    
    public static EnumStatusArticleAcheteDistribue getValueByIndex(int index) {
    	EnumStatusArticleAcheteDistribue status = null;
        for(EnumStatusArticleAcheteDistribue s : values()) {
            if(s.getIndex() == index) {
                status = s;
            }
        }
		return status;
    }
    
    public static EnumStatusArticleAcheteDistribue getEnumByIndex(int index) {
        for(EnumStatusArticleAcheteDistribue status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
