package xiaolan.daigou.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import  xiaolan.daigou.common.serialize.StatusArticleDeserializer;

import xiaolan.daigou.common.enums.inter.BaseEnum;

@JsonDeserialize(using = StatusArticleDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusArticle implements BaseEnum{
    NON_PREPARE("1", "Non préparé", DaigouConstant.CSS_RED),
    PREPARE_PARTIE("2", "Préparé une partie", DaigouConstant.CSS_ORANGE),
    PREPARE_BIEN("3", "Tout préparé", DaigouConstant.CSS_VERT),
    QTE_INCORRECT("4", "Nombre incorrect", DaigouConstant.CSS_RED);

    private String index;
    private String value;
    private String color;

    private EnumStatusArticle(String index, String value, String color){
        this.index = index;
        this.value = value;
        this.color = color;
    }

    public static String getValueByIndex(int index) {
        String value = null;
        for(EnumStatusArticle status : values()) {
            if(status.getIndex() == String.valueOf(index)) {
                value = status.getValue();
            }
        }
        return value;
    }

    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
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
