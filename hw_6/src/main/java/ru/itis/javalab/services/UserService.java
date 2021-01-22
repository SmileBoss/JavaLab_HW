package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;

public interface UserService {
    UserDto singIn(UserForm userForm);
    void singUp(UserForm userForm);
    Integer getUserByEmail(String email);
    void updateUser(User entity);
    String getPasswordById(Integer id);
}
