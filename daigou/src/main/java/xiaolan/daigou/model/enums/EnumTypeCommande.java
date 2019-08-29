package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusArticlePreparationDeserializer;
import xiaolan.daigou.common.serialize.TypeCommandeDeserializer;

@JsonDeserialize(using = TypeCommandeDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumTypeCommande {
    COMMANDE_CLIENT(1, "Commande pour client"),
    COMMANDE_STOCKAGE(2, "Commande pour stockage");


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
	
    public static EnumTypeCommande getEnumByIndex(int index) {
        for(EnumTypeCommande status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
