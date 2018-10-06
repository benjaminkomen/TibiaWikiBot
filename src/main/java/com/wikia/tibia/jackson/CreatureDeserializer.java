package com.wikia.tibia.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wikia.tibia.objects.Creature;

import java.io.IOException;

/**
 * This is not used yet, but it could be in the future.
 */
public class CreatureDeserializer extends StdDeserializer<Creature> {

    public CreatureDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Creature deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String illusionableString = node.get("illusionable").asText();
        Boolean illusionable = null;
        if ("yes".equalsIgnoreCase(illusionableString)) {
            illusionable = true;
        }
        if ("no".equalsIgnoreCase(illusionableString)) {
            illusionable = false;
        }
//        return new Creature(illusionable);
        return new Creature();
    }
}