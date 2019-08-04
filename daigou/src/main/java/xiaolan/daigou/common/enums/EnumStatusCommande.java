package xiaolan.daigou.common.enums;

public enum EnumStatusCommande {
    NEW_COMMANDE("Nouvelle commande", 1, DaigouConstant.CSS_RED, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET("Partie prêt", 2, DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET_A_ENVOYER("Partie prêt à envoyer", 3, DaigouConstant.CSS_JAUNE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PRET_A_ENVOYER("Commande prêt à envoyer", 4, DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_SUR_LA_ROUTE("Commande sur la route", 5, DaigouConstant.CSS_JAUNE, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_MANQUE_INFO_CLIENT("Commande manque info client", 7, DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_PRET_A_DISTRIBUER("Commande prêt à distribuer", 8, DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    TERMINE("Terminé", 9, DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_TERMINEE);

    private String value;
    private int index;
    private String color;
    private EnumStatusCommandeGroup group;

    private EnumStatusCommande(String value, int index, String color, EnumStatusCommandeGroup group){
        this.value = value;
        this.index = index;
        this.color = color;
        this.group = group;
    }

    public static String getValueByIndex(int index) {
        String value = null;
        for(EnumStatusCommande status : values()) {
            if(status.getIndex() == index) {
                value = status.getValue();
            }
        }
        return value;
    }

    public String getValue(){
        return this.value;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public String getColor(){
    	return this.color;
    }
    
    public EnumStatusCommandeGroup getGroup(){
        return this.group;
    }

    public boolean isInGroup(EnumStatusCommandeGroup group) {
        return this.group == group;
    }
}
