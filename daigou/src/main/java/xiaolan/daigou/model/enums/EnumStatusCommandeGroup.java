package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusCommandeGroupDeserializer;

@JsonDeserialize(using = StatusCommandeGroupDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusCommandeGroup {
	COMMANDE_NON_ENVOYEE("enum.commandeGroup.commandeNonEnvoyee"),
    COMMANDE_ENVOYEE("enum.commandeGroup.commandeEnvoyeeSurLaRoute"),
    COMMANDE_ARRIVEE("enum.commandeGroup.commandeArrivee"),
    COMMANDE_TERMINEE("enum.commandeGroup.commandeTerminee");
    
    private String value;
    
    private EnumStatusCommandeGroup(String value) {
    	this.value = value;
    }
    
    public String getValue() {
    	return this.value;
    }
}