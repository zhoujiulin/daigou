package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusColis;

@Converter
public class StatusColisConverter  implements AttributeConverter<EnumStatusColis, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusColis status) {
        return status.getIndex();
    }

    @Override
    public EnumStatusColis convertToEntityAttribute(Integer index) {
        return EnumStatusColis.getEnumByIndex(index);
    }
}
