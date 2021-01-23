package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    User findById(Integer id);

    void save(T entity);
    void update(T entity);
    void deleteById(Integer id);
    void delete(T entity);



}
