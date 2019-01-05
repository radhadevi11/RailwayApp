package com.radha.railway.dao;

public interface Dao<T> {
    int save(T entity);
    T get(int key);
}
