package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.model.enums.EnumStatusCommande;

@Converter
public class StatusCommandeConverter  implements AttributeConverter<EnumStatusCommande, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusCommande status) {
        return status.getIndex();
    }

	@Override
	public EnumStatusCommande convertToEntityAttribute(Integer index) {
		return EnumStatusCommande.getEnumByIndex(index);
	}
}
