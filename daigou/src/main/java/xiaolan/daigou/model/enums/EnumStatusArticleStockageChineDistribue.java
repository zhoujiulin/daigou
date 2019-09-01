package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticleAcheteDistribueDeserializer;

@JsonDeserialize(using = StatusArticleAcheteDistribueDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticleStockageChineDistribue {
	ARTICLE_FROM_STOCKAG_CHINE_NON_DISTRIBUE(1, "enum.article.statusDistribue.articleFromStockageChineNonDistribue", DaigouConstant.CSS_ORANGE),
	ARTICLE_FROM_STOCKAG_CHINE_DISTRIBUE(2, "enum.article.statusDistribue.articleFromStockageChineDistribue", DaigouConstant.CSS_VERT);
    
	private int index;
    private String value;
    private String color;
    
    private EnumStatusArticleStockageChineDistribue(int index, String value, String color) {
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
    
    public static EnumStatusArticleStockageChineDistribue getValueByIndex(int index) {
    	EnumStatusArticleStockageChineDistribue status = null;
        for(EnumStatusArticleStockageChineDistribue s : values()) {
            if(s.getIndex() == index) {
                status = s;
            }
        }
		return status;
    }
    
    public static EnumStatusArticleStockageChineDistribue getEnumByIndex(int index) {
        for(EnumStatusArticleStockageChineDistribue status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
