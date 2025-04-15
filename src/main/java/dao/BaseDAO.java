package dao;

import java.util.List;

public interface BaseDAO<T> {
    List<T> getAll();
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    T getById(int id);
}