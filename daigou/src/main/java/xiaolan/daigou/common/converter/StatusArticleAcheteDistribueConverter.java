package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.model.enums.EnumStatusArticleAcheteDistribue;

@Converter
public class StatusArticleAcheteDistribueConverter  implements AttributeConverter<EnumStatusArticleAcheteDistribue, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusArticleAcheteDistribue status) {
	   if(status == null) {
		   return null;
	   }
       return status.getIndex();
    }

    @Override
    public EnumStatusArticleAcheteDistribue convertToEntityAttribute(Integer index) {
    	if(index == null) {
    		return null;
    	}
        return EnumStatusArticleAcheteDistribue.getEnumByIndex(index);
    }
}
