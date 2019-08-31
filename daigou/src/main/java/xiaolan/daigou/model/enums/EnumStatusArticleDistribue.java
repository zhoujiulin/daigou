package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticleDistribueDeserializer;

@JsonDeserialize(using = StatusArticleDistribueDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticleDistribue {
	ARTICLE_ACHETE_DISTRIBUE(1, "enum.article.statusDistribue.articleAcheteDistribue", DaigouConstant.CSS_VERT),
	ARTICLE_FROM_STOCKAG_CHINE_DISTRIBUE(2, "enum.article.statusDistribue.articleFromStockageChineDistribue", DaigouConstant.CSS_VERT);
    
	private int index;
    private String value;
    private String color;
    
    private EnumStatusArticleDistribue(int index, String value, String color) {
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
    
    public static EnumStatusArticleDistribue getValueByIndex(int index) {
    	EnumStatusArticleDistribue status = null;
        for(EnumStatusArticleDistribue s : values()) {
            if(s.getIndex() == index) {
                status = s;
            }
        }
		return status;
    }
    
    public static EnumStatusArticleDistribue getEnumByIndex(int index) {
        for(EnumStatusArticleDistribue status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
