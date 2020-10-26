package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> getAllUsers();
    List<User> getAllUsersByAge(int age);

}
