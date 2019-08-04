package xiaolan.daigou.common.enums;

public enum EnumTypeCommande {
    COMMANDE_CLIENT(1, "Commande pour client"),
    COMMANDE_SANS_CLIENT(2, "Commande sans client");


    private int index;
    private String libelle;
    
    EnumTypeCommande(int index, String libelle){
        this.index = index;
        this.libelle = libelle;
    }

    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
