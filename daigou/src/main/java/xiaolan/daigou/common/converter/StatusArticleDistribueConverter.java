package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.model.enums.EnumStatusArticleDistribue;

@Converter
public class StatusArticleDistribueConverter  implements AttributeConverter<EnumStatusArticleDistribue, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusArticleDistribue status) {
        return status.getIndex();
    }

    @Override
    public EnumStatusArticleDistribue convertToEntityAttribute(Integer index) {
    	if(index == null) {
    		return null;
    	}
        return EnumStatusArticleDistribue.getEnumByIndex(index);
    }
}
