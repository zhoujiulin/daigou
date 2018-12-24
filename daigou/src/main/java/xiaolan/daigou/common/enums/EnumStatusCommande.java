package xiaolan.daigou.common.enums;

public enum EnumStatusCommande {
    NEW_COMMANDE("Nouvelle commande", 1),
    COMMANDE_PARTIE_PRET("Partie prêt", 2),
    COMMANDE_PARTIE_PRET_A_ENVOYER("Partie prêt à envoyer", 3),
    COMMANDE_PRET_A_ENVOYER("Commande prêt à envoyer", 4),
    EN_COURS_DE_ENVOYER("En cours d'envoyer", 5),
    COMMANDE_MANQUE_INFO_CLIENT("Commande manque info client", 7),
    COMMANDE_PRET_A_DISTRIBUER("Commande prêt à distribuer", 8),
    TERMINE("Terminé", 9);

    private String value;
    private int index;

    private EnumStatusCommande(String value, int index){
        this.value = value;
        this.index = index;
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
}
