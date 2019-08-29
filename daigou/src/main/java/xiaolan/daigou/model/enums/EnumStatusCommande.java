package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusCommandeDeserializer;

@JsonDeserialize(using = StatusCommandeDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusCommande {
    ERREUR_IN_COMMANDE(0, "enum.commande.erreurInCommande", DaigouConstant.CSS_RED, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    NEW_COMMANDE(1, "enum.commande.nouvelleCommande", DaigouConstant.CSS_RED, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET(2, "enum.commande.partiePret", DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET_A_ENVOYER(3, "enum.commande.partiePretAEnvoyer", DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PRET_A_ENVOYER(4, "enum.commande.commandePretAEnvoyer", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_SUR_LA_ROUTE(5, "enum.commande.commandeSurLaRoute", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_ENVOYEE),
    COMMANDE_MANQUE_INFO_CLIENT(6, "enum.commande.commandeManqueInfoClient", DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_ARRIVEE),
    COMMANDE_PRET_A_DISTRIBUER(7, "enum.commande.commandePretADistribuer", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_ARRIVEE),
    TERMINE(8, "enum.commande.termine", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_TERMINEE);

    private int index;
    private String value;
    private String color;
    private EnumStatusCommandeGroup group;

    private EnumStatusCommande(int index, String value, String color, EnumStatusCommandeGroup group){
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
    
    public static EnumStatusCommande getEnumByIndex(int index) {
        for(EnumStatusCommande status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
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
