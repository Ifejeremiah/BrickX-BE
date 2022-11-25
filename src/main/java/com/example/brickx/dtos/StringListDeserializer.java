package com.example.brickx.dtos;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        List<String> ret = new ArrayList<>();

        ObjectCodec codec = jsonParser.getCodec();
        TreeNode node = codec.readTree(jsonParser);

        if (node.isArray()) {
            for (JsonNode n : (ArrayNode) node) {
                ret.add(n.asText());
            }
        } else if (node.isValueNode()) {
            ret.add(((JsonNode) node).asText());
        }
        return ret;
    }
}

