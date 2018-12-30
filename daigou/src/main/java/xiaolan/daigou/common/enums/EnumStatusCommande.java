package xiaolan.daigou.common.enums;

public enum EnumStatusCommande {
    NEW_COMMANDE("Nouvelle commande", 1, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET("Partie prêt", 2, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET_A_ENVOYER("Partie prêt à envoyer", 3, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PRET_A_ENVOYER("Commande prêt à envoyer", 4, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    EN_COURS_DE_ENVOYER("En cours d'envoyer", 5, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_MANQUE_INFO_CLIENT("Commande manque info client", 7, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_PRET_A_DISTRIBUER("Commande prêt à distribuer", 8, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    TERMINE("Terminé", 9, EnumStatusCommandeGroup.COMMANDE_TERMINEE);

    private String value;
    private int index;
    private EnumStatusCommandeGroup group;

    private EnumStatusCommande(String value, int index, EnumStatusCommandeGroup group){
        this.value = value;
        this.index = index;
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
    
    public EnumStatusCommandeGroup getGroup(){
        return this.group;
    }

    public boolean isInGroup(EnumStatusCommandeGroup group) {
        return this.group == group;
    }
}
