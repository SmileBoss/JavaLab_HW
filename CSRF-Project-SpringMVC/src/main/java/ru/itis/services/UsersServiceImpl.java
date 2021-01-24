package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UsersServiceImpl implements UsersService {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findOne(id);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.findOne(userId).ifPresent(user -> {
            user.setIsDeleted(true);
            userRepository.update(user);
        });
    }

    @Override
    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findOneByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;

    }

    @Override
    public void signUp(User user) {
        String confirmCode = UUID.randomUUID().toString();

        user.setConfirmCode(confirmCode);

        String url = "https://itdrive.pro/confirm/" + confirmCode;

        userRepository.save(user);
        emailService.sendMail("Подтверждение регистрации", url, user.getEmail());

    }
}
