package com.org.mycache.core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.org.mycache.core.http.model.Header;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class HttpRemoteUtil {

    private static final Header defaultHeader=new Header() {
        {
        add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        add("accept", MediaType.APPLICATION_JSON_VALUE);
        }
    };
    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static <T>  T  get(String url,Class<T> clazz,Map<String,Object> params,Header header) throws UnirestException {
        return Unirest.get(url).headers(header.values()).queryString(params).asObject(clazz).getBody();
    }

    public static <T,I> T post(String url,Class<T> clazz,Class<I> payloadClazz,I payload,Map<String,Object> params,Header header) throws UnirestException {
        return Unirest.post(url).headers(header.values()).queryString(params).body(payload).asObject(clazz).getBody();
    }

    public static Header defaultHeaders() {
        return defaultHeader;
    }
}
