package dao;

import models.Student;

import java.util.*;

public interface BaseDAO<T> {
    List<T> getAll(); // Read

    void add(T entity); // Create

    void update(T entity, String[] params); // Update

    void delete(T entity); // Delete

    Optional<T> getById(int id); // Поиск по ID
}