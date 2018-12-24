package xiaolan.daigou.common.enums;

public enum EnumTypeCommande {
    COMMANDE_POUR_CLIENT(1),
    COMMANDE_POUR_STOCKAGE(2);


    private int index;

    EnumTypeCommande(int index){
        this.index = index;
    }

    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }
}
