package xiaolan.daigou.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import  xiaolan.daigou.common.serialize.StatusArticlePreparationDeserializer;

@JsonDeserialize(using = StatusArticlePreparationDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticlePreparation{
    NON_PREPARE(1, "enum.article.statusPreparation.nonPrepare", DaigouConstant.CSS_RED),
    PREPARE_PARTIE(2, "enum.article.statusPreparation.prepareUnePartie", DaigouConstant.CSS_ORANGE),
    PREPARE_TOUT(3, "enum.article.statusPreparation.toutPrepare", DaigouConstant.CSS_VERT),
    STOCKAGE(4, "enum.article.statusPreparation.stockage", DaigouConstant.CSS_VERT),
    QTE_INCORRECT(5, "enum.article.statusPreparation.nombreIncorrect", DaigouConstant.CSS_RED);

    private int index;
    private String value;
    private String color;

    private EnumStatusArticlePreparation(Integer index, String value, String color){
        this.index = index;
        this.value = value;
        this.color = color;
    }

    public static String getValueByIndex(int index) {
        String value = null;
        for(EnumStatusArticlePreparation status : values()) {
            if(status.getIndex() == index) {
                value = status.getValue();
            }
        }
        return value;
    }
    
    public static EnumStatusArticlePreparation getEnumByIndex(int index) {
        for(EnumStatusArticlePreparation status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }

    public void setIndex(int index){
        this.index = index;
    }
    public Integer getIndex(){
        return this.index;
    }

    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    
    public String getColor(){
    	return this.color;
    }
}
