package ru.itis.services;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    void addNewUser(User user);
    void deleteUserById(Integer id);
    void updateUserById(User user);
}
