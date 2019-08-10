package xiaolan.daigou.common.enums;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusCommandeGroupDeserializer;

@JsonDeserialize(using = StatusCommandeGroupDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusCommandeGroup {
	COMMANDE_NON_ENVOYEE("Commande non envoyé"),
    COMMANDE_ENVOYEE("Commande envoyée sur la route"),
    COMMANDE_ARRIVEE("Commande arrivé"),
    COMMANDE_TERMINEE("Commande terminé");
    
    private String value;
    
    private EnumStatusCommandeGroup(String value) {
    	this.value = value;
    }
    
    public String getValue() {
    	return this.value;
    }
}