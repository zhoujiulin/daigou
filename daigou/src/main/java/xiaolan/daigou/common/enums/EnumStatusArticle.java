package xiaolan.daigou.common.enums;

public enum EnumStatusArticle {
    NON_PREPARE("Non préparé", 1),
    PREPARE_PARTIE("Préparé une partie", 2),
    PREPARE_BIEN("Tout préparé", 3);


    private String value;
    private int index;

    private EnumStatusArticle(String value, int index){
        this.index = index;
        this.value = value;
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
}
