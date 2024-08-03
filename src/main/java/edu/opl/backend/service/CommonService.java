package edu.opl.backend.service;

import java.util.List;

public interface CommonService<T,V> {
    T create(V value);
    List<T> findAll();
    void update(T value);
    void delete(T value);
}
