package xiaolan.daigou.common.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;

public class StatusArticlePreparationDeserializer extends JsonDeserializer<EnumStatusArticlePreparation> {

	@Override
	public EnumStatusArticlePreparation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        JsonNode nodeIndex = node.get("index");
        if (nodeIndex == null) {
        	return null;
        }
        
        int index = Integer.valueOf(nodeIndex.toString());
        for(EnumStatusArticlePreparation status : EnumStatusArticlePreparation.values()) {
        	if(index == status.getIndex()) {
        		return status;
        	}
        }
        
		return null;
	}

}
