package xiaolan.daigou.common.enums;

public enum EnumStatusArticle {
    NON_PREPARE("Non préparé", 1, DaigouConstant.CSS_RED),
    PREPARE_PARTIE("Préparé une partie", 2, DaigouConstant.CSS_ORANGE),
    PREPARE_BIEN("Tout préparé", 3, DaigouConstant.CSS_VERT),
    QTE_INCORRECT("Nombre incorrect", 4, DaigouConstant.CSS_RED);


    private String value;
    private int index;
    private String color;

    private EnumStatusArticle(String value, int index, String color){
        this.index = index;
        this.value = value;
        this.color = color;
    }

    public static String getValueByIndex(int index) {
        String value = null;
        for(EnumStatusArticle status : values()) {
            if(status.getIndex() == index) {
                value = status.getValue();
            }
        }
        return value;
    }

    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
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
