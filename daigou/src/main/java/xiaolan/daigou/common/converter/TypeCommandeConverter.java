package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.common.enums.EnumTypeCommande;

@Converter
public class TypeCommandeConverter  implements AttributeConverter<EnumTypeCommande, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumTypeCommande type) {
        return type.getIndex();
    }

	@Override
	public EnumTypeCommande convertToEntityAttribute(Integer type) {
		return EnumTypeCommande.getEnumByIndex(type);
	}
}
