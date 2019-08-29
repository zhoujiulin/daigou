package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.model.enums.EnumStatusArticle;

@Converter
public class StatusArticleConverter  implements AttributeConverter<EnumStatusArticle, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusArticle status) {
        return status.getIndex();
    }

    @Override
    public EnumStatusArticle convertToEntityAttribute(Integer index) {
        return EnumStatusArticle.getEnumByIndex(index);
    }
}
