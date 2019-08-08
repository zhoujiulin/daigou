package xiaolan.daigou.common.enums;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticleDeserializer;
import xiaolan.daigou.common.serialize.StatusCommandeDeserializer;
import xiaolan.daigou.common.serialize.StatusCommandeGroupDeserializer;

@JsonDeserialize(using = StatusCommandeDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusCommande {
    NEW_COMMANDE(1, "Nouvelle commande", DaigouConstant.CSS_RED, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET(2, "Partie prêt", DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PARTIE_PRET_A_ENVOYER(3, "Partie prêt à envoyer", DaigouConstant.CSS_JAUNE, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_PRET_A_ENVOYER(4, "Commande prêt à envoyer", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_NON_ENVOYEE),
    COMMANDE_SUR_LA_ROUTE(5, "Commande sur la route", DaigouConstant.CSS_JAUNE, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_MANQUE_INFO_CLIENT(7, "Commande manque info client", DaigouConstant.CSS_ORANGE, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    COMMANDE_PRET_A_DISTRIBUER(8, "Commande prêt à distribuer", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_NON_ARRIVEE),
    TERMINE(9, "Terminé", DaigouConstant.CSS_VERT, EnumStatusCommandeGroup.COMMANDE_TERMINEE);

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
