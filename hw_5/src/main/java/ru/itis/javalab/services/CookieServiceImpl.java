package ru.itis.javalab.services;

import ru.itis.javalab.models.CookieUser;
import ru.itis.javalab.repositories.CookieRepository;

import java.util.List;
import java.util.Optional;

public class CookieServiceImpl implements CookieService {

    public CookieServiceImpl(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    CookieRepository cookieRepository;
    @Override
    public void remove(CookieUser cookieUser) {
        cookieRepository.delete(cookieUser);
    }

    @Override
    public void update(CookieUser cookieUser) {
        cookieRepository.update(cookieUser);
    }

    @Override
    public void removeByUserId(Integer id) {
        cookieRepository.deleteById(id);
    }

    @Override
    public List<CookieUser> getAllCookie() {
        return cookieRepository.findAll();
    }

    @Override
    public Optional<CookieUser> getByUserId(Integer id) {
        return cookieRepository.findById(id);
    }

    @Override
    public Optional<CookieUser> getByAuth(String auth) {
        return cookieRepository.findByAuth(auth);
    }

    @Override
    public void addCookie(CookieUser cookieUser) {
        cookieRepository.save(cookieUser);
    }
}
