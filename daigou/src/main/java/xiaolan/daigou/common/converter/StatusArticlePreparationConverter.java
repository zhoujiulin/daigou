package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.common.enums.EnumStatusArticlePreparation;

@Converter
public class StatusArticlePreparationConverter  implements AttributeConverter<EnumStatusArticlePreparation, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusArticlePreparation status) {
        return status.getIndex();
    }

    @Override
    public EnumStatusArticlePreparation convertToEntityAttribute(Integer index) {
        return EnumStatusArticlePreparation.getEnumByIndex(index);
    }
}
