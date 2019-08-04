package xiaolan.daigou.common.enums;

public enum EnumStatusColis {
	COLIS_NON_ENVOYE(1, "Colis non envoyé"),
    COLIS_ENVOYE_VERS_CHINE(2, "Commande envoyé vers Chine"),
    COLIS_ARRIVE_EN_CHINE(3, "Commande arrivé en Chine"),
    COLIS_TERMINE(4, "Commande terminé");
    
	private int index;
    private String value;
    
    private EnumStatusColis(int index, String value) {
    	this.index = index;
    	this.value = value;
    }
    
    public int getIndex() {
    	return this.index;
    }
    
    public String getValue() {
    	return this.value;
    }
}
