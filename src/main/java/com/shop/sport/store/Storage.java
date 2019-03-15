package com.shop.sport.store;

import com.shop.sport.domain.Product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Storage {
    private static final Storage INSTANCE = new Storage();
    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator;

    private Storage() {
        Product nike = new Product(1, "Nike", "sneakers", 95);
        Product adidas = new Product(2, "Adidas", "sneakers", 100);
        Product puma = new Product(3, "Puma", "sneakers", 85);
        products.add(nike);
        products.add(adidas);
        products.add(puma);
        idGenerator = new AtomicLong(products.size());
    }

    public long getNextid() {
        return idGenerator.incrementAndGet();
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    public List<Product> getProducts() {
        return products;
    }
}
