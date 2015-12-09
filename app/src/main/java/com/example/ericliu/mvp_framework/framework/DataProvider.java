package com.example.ericliu.mvp_framework.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericliu on 9/12/2015.
 */
public enum DataProvider {
    INSTANCE;


    private Map<Class<?>, Map<String, Object>> tables = new HashMap<>();
//    private Map<String, Object> rows = new HashMap<>();


    /**
     * save an value into data provider
     *
     * @param transactionId
     * @param data
     * @param <T>
     */
    public <T> void save(String transactionId, T data) {
        if (transactionId == null) {
            throw new IllegalArgumentException("transactionId should not be null.");
        } else if (data == null) {
            throw new IllegalArgumentException("data should not be null.");
        }

//        rows.put(transactionId, data);
        if (!tables.containsKey(data.getClass())) {
            tables.put(data.getClass(), rows);
        }

    }

    public Object get(String transactionId) {

    }

}
