package com.shop.sport.domain;

import com.shop.sport.dao.Dao;
import com.shop.sport.store.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements Dao<Product> {
    private final static ProductDao INSTANCE = new ProductDao();
    private final Storage storage = Storage.getInstance();

    public static ProductDao getInstance() {
        return INSTANCE;
    }

    private ProductDao() {
    }

    @Override
    public Optional<Product> get(long id) {
        return storage.getProducts()
                .stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    @Override
    public void save(Product entity) {
        long nextId = storage.getNextid();
        entity.setId(nextId);
        storage.getProducts().add(entity);

    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage.getProducts());
    }

    @Override
    public void update(Product entity) {
        get(entity.getId())
                .ifPresent(product -> {
                    product.setName(entity.getName());
                    product.setType(entity.getType());
                    product.setPrice(entity.getPrice());
                });

    }

    @Override
    public void delete(long id) {
        storage.getProducts().removeIf(product -> product.getId() == id);

    }
}
