package ru.itis.services;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendMail(String subject, String text, String email) {
        System.err.println("Сообщение: <" + text + "> было отправлено на <" + email + ">");
    }
}
