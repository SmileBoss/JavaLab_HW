package ru.itis.repositories;

import ru.itis.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    Optional<User> findOneByEmail(String email);
}
