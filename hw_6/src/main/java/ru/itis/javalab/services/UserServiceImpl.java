package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto singIn(UserForm userForm) {
        Optional<User> userOptional = userRepository.findByEmail(userForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
                return UserDto.from(user);
            } else return null;
        }
        return null;
    }

    @Override
    public void singUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .nickname(userForm.getNickname())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public Integer getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            User user1 = user.get();
            return user1.getId();
        }
        return null;
    }

    @Override
    public void updateUser(User entity) {
        User user = User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .password(passwordEncoder.encode(entity.getPassword()))
                .build();
        userRepository.update(user);
    }

    @Override
    public String getPasswordById(Integer id) {
       Optional<User> user = userRepository.findById(id);
        return user.map(User::getPassword).orElse(null);
    }
}
