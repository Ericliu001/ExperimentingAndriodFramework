package com.example.ericliu.mvp_framework.framework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * Created by ericliu on 10/12/2015.
 */
public class DataProviderTest {

    private Shop mShop;

    @Before
    public void setUp() throws Exception {
        mShop = new Shop();
    }

    @After
    public void tearDown() throws Exception {
        mShop = null;
    }

    @Test
    public void testSave() throws Exception {
        String transactionId = UUID.randomUUID().toString();


        DataProvider.INSTANCE.save(transactionId, mShop);

        Field tablesField = DataProvider.class.getDeclaredField("tables");
        tablesField.setAccessible(true);
        Map<Class<?>, Map> tables = (Map<Class<?>, Map>) tablesField.get(DataProvider.INSTANCE);

        assertTrue("class not added to table", tables.containsKey(Shop.class));

        Map<String, Object> rows = tables.get(Shop.class);
        Shop shop2 = (Shop) rows.get(transactionId);

        assertTrue("saved instance not the same instance", mShop.equals(shop2));
    }

    @Test
    public void testGet() throws Exception {
        DataProvider.INSTANCE.save("stuff", mShop);

        Shop shop = DataProvider.INSTANCE.get("stuff", Shop.class);
        assertTrue(shop.equals(mShop));
    }


    private static class Shop {}
}
