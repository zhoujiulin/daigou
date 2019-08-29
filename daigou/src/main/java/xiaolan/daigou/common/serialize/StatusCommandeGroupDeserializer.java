package xiaolan.daigou.common.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.model.enums.EnumStatusCommande;
import xiaolan.daigou.model.enums.EnumStatusCommandeGroup;

public class StatusCommandeGroupDeserializer extends JsonDeserializer<EnumStatusCommandeGroup> {

	@Override
	public EnumStatusCommandeGroup deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        String text = node.textValue();

        if (text == null) {
            return null;
        }

		return null;
	}

}
