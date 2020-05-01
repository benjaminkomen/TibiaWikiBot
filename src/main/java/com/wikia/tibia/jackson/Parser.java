package com.wikia.tibia.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.wikia.tibia.objects.WrappedWikiObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Parser {

    private static ObjectMapper defaultObjectMapper;

    private Parser() {
        // don't instantiate this class, it has only static members
    }

    public static <T> T parse(Class<T> type, String json) {
        return parse(type, getDefaultObjectMapper(), json);
    }

    public static <T> T parse(Class<T> type, ObjectMapper mapper, String json) {
        if (json == null || json.equals("")) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructType(type);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new JacksonParsingException(e);
        }
    }

    public static Object parse(String json) {
        return parse(json, getDefaultObjectMapper());
    }

    public static Object parse(String json, ObjectMapper mapper) {
        return parse(Object.class, mapper, json);
    }

    public static <T> List<T> list(Class<T> type, String json) {
        return list(type, getDefaultObjectMapper(), json);
    }

    public static <T> List<T> list(Class<T> type, ObjectMapper mapper, String json) {

        if (json == null || "".equals(json)) {
            return Collections.emptyList();
        }

        List<T> infos;
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        try {
            infos = mapper.readValue(json, collectionType);
        } catch (IOException e) {
            throw new JacksonParsingException(e);
        }

        return infos != null
                ? infos
                : Collections.emptyList();
    }

    /**
     * Alternative implementation of {@link #list(Class, String)} where the json list is read one-by-one, which makes
     * it more robust against jsonparsingexceptions
     */
    public static <T> List<T> listOneByOne(Class<T> type, String json, long limit) {
        return listOneByOne(type, getDefaultObjectMapper(), json, limit);
    }

    private static <T> List<T> listOneByOne(Class<T> type, ObjectMapper mapper, String json, long limit) {
        if (json == null || "".equals(json)) {
            return Collections.emptyList();
        }

        limit = limit != -1
                ? limit
                : Long.MAX_VALUE;

        try {
            JsonNode jsonAsNode = mapper.readTree(json);

            if (jsonAsNode.isArray()) {
                return IntStream.range(0, jsonAsNode.size())
                        .mapToObj(jsonAsNode::get)
                        .map(jn -> {
                            try {
                                T value = mapper.treeToValue(jn, type);
                                if (value instanceof WrappedWikiObject) {
                                    ((WrappedWikiObject) value).setOriginalJson(new JSONObject(value));
                                }
                                return value;
                            } catch (JsonProcessingException e) {
                                log.error("Unable to construct object of type '{}' from the following json: {} because of error: {}", type, jn, e);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .limit(limit)
                        .collect(Collectors.toList());
            } else {
                log.error("Retrieved json is not a json array, cannot parse to list.");
                return Collections.emptyList();
            }
        } catch (IOException e) {
            throw new JacksonParsingException(e);
        }
    }

    public static String json(Object object) {
        return json(object, getDefaultObjectMapper());
    }

    public static String json(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialise object to json", e);
            return "";
        }
    }

    private static ObjectMapper getDefaultObjectMapper() {
        if (defaultObjectMapper == null) {
            defaultObjectMapper = new ObjectMapper();
            defaultObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Value that indicates that only properties with non-null values are to be included.
        }
        return defaultObjectMapper;
    }

    private static class JacksonParsingException extends RuntimeException {
        public JacksonParsingException(Exception e) {
            super(e);
        }
    }
}
