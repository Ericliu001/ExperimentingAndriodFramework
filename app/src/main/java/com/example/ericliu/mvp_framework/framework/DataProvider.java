package com.example.ericliu.mvp_framework.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericliu on 9/12/2015.
 */
public enum DataProvider {
    INSTANCE;


    private Map<Class<?>, Map> tables = new HashMap<>();


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

        if (!tables.containsKey(data.getClass())) {
            Map<String, T> rows = new HashMap<>();
            tables.put(data.getClass(), rows);
        }

        tables.get(data.getClass()).put(transactionId, data);

    }

    public <T> T get(String transactionId, Class<T> type) {
        Map<String, T> rows = tables.get(type);
        if (rows == null) {
            return null;
        }
        return rows.get(transactionId);
    }

}
