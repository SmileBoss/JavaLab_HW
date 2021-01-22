package ru.itis.javalab.repositories;

import ru.itis.javalab.models.CookieUser;

import java.util.Optional;

public interface CookieRepository extends CrudRepository<CookieUser> {
    Optional<CookieUser> findByAuth(String auth);
    void deleteById(Integer id);
}
