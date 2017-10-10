package com.org.mycache.core.http.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class Header {
    private Map<String,String> headers=new HashMap<>(10);
    public void add(String name,String value) {
        headers.put(name,value);
    }

    public Map<String,String> values() {
        return headers;
    }
}
