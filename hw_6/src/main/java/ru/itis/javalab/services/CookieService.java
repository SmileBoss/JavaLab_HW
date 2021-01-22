package ru.itis.javalab.services;

import ru.itis.javalab.models.CookieUser;

import java.util.List;
import java.util.Optional;

public interface CookieService {
    void remove(CookieUser cookieUser);
    void update(CookieUser cookieUser);
    void removeByUserId(Integer id);
    List<CookieUser> getAllCookie();
    Optional<CookieUser> getByUserId(Integer id);
    Optional<CookieUser> getByAuth(String auth);
    void addCookie(CookieUser cookieUser);
}
