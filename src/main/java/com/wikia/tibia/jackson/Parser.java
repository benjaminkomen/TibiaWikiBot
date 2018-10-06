package com.wikia.tibia.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
        List<T> infos;
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        try {
            infos = mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new JacksonParsingException(e);
        }

        return infos != null
                ? infos
                : Collections.emptyList();
    }

    private static ObjectMapper getDefaultObjectMapper() {
        if (defaultObjectMapper == null) {
            defaultObjectMapper = new ObjectMapper();
        }
        return defaultObjectMapper;
    }

    private static class JacksonParsingException extends RuntimeException {
        public JacksonParsingException(Exception e) {
            super(e);
        }
    }
}
