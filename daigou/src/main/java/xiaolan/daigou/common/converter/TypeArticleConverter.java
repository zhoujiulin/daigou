package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.common.enums.EnumTypeArticle;

@Converter
public class TypeArticleConverter  implements AttributeConverter<EnumTypeArticle, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumTypeArticle type) {
        return type.getIndex();
    }

	@Override
	public EnumTypeArticle convertToEntityAttribute(Integer type) {
		return EnumTypeArticle.getEnumByIndex(type);
	}
}
