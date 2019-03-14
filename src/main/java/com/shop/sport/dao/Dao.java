package com.shop.sport.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    void save(T entity);

    List<T> findAll();

    void update(T entity);

    void delete(long id);

}
