package com.radha.railway.dao;

import java.util.List;

public interface Dao<T> {
    int save(T entity);
    T get(int key);
    List<T> getAll();
}
