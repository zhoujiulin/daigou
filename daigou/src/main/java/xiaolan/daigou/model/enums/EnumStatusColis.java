package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.StatusColisDeserializer;

@JsonDeserialize(using = StatusColisDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumStatusColis {
	COLIS_NON_ENVOYE(1, "enum.colis.colisNonEnvoye"),
    COLIS_ENVOYE_VERS_CHINE(2, "enum.colis.colisEnvoyeVersChine"),
    COLIS_ARRIVE_EN_CHINE(3, "enum.colis.colisArriveEnChine"),
    COLIS_TERMINE(4, "enum.colis.colisTermine");
    
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
    
    public static EnumStatusColis getValueByIndex(int index) {
    	EnumStatusColis status = null;
        for(EnumStatusColis s : values()) {
            if(s.getIndex() == index) {
                status = s;
            }
        }
		return status;
    }
    
    public static EnumStatusColis getEnumByIndex(int index) {
        for(EnumStatusColis status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
