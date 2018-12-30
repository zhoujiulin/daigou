package xiaolan.daigou.common.enums;

public enum EnumStatusCommandeGroup {
	COMMANDE_NON_ENVOYEE("Commande non envoyé"),
    COMMANDE_NON_ARRIVEE("Commande non arrivée"),
    COMMANDE_TERMINEE("Commande terminé");
    
    private String value;
    
    private EnumStatusCommandeGroup(String value) {
    	this.value = value;
    }
    
    public String getValue() {
    	return this.value;
    }
}
