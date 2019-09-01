package xiaolan.daigou.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import xiaolan.daigou.model.enums.EnumStatusArticleStockageChineDistribue;

@Converter
public class StatusArticleStockageChineDistribueConverter  implements AttributeConverter<EnumStatusArticleStockageChineDistribue, Integer> {
	
   @Override
    public Integer convertToDatabaseColumn(EnumStatusArticleStockageChineDistribue status) {
	   if(status == null) {
		   return null;
	   }
       return status.getIndex();
    }

    @Override
    public EnumStatusArticleStockageChineDistribue convertToEntityAttribute(Integer index) {
    	if(index == null) {
    		return null;
    	}
        return EnumStatusArticleStockageChineDistribue.getEnumByIndex(index);
    }
}
