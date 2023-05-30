package com.example.prova_web.Repository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import java.util.List;

public class ItemRepository {

    public List<Item> findByDeletedIsNull() {
        return null;
    }

}
