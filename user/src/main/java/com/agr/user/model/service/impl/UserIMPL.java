package com.agr.user.model.service.impl;

import com.agr.user.model.dao.UserDao;
import com.agr.user.model.entity.User;
import com.agr.user.model.service.IUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserIMPL implements IUser {

    @Autowired
    private UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final int MAX_INTENTOS_FALLIDOS = 3;

    @Transactional
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        Optional<User> existingUser = userDao.findById(user.getId());
        if (existingUser.isPresent()) {
            return userDao.save(user);
        } else {
            throw new EntityNotFoundException("Usuario con ID " + user.getId() + " no encontrado");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        Iterable<User> iterable = userDao.findAll();
        List<User> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        userDao.delete(user);
    }

    @Transactional
    @Override
    public User login(String usernameOrEmail, String password) throws Exception {
        Optional<User> optionalUser = userDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (optionalUser.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }

        User user = optionalUser.get();

        if (!user.getStatus()) {
            throw new Exception("Usuario bloqueado");
        }

        if (user.getSessionActive()) {
            throw new Exception("Ya tiene una sesión activa");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {

            int intentos = Optional.ofNullable(user.getIntentosFallidos()).orElse(0) + 1;
            user.setIntentosFallidos(intentos);

            if (intentos >= MAX_INTENTOS_FALLIDOS) {
                user.setStatus(false); // Bloquear usuario
                userDao.save(user);
                throw new Exception("Usuario bloqueado tras " + MAX_INTENTOS_FALLIDOS + " intentos fallidos");
            } else {
                userDao.save(user);
                throw new Exception("Contraseña incorrecta");
            }
        }

        user.setIntentosFallidos(0);
        user.setSessionActive(true);
        userDao.save(user);

        return user;
    }

    @Transactional
    @Override
    public void logout(Long userId) throws Exception {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        if (!user.getSessionActive()) {
            throw new Exception("El usuario no tiene sesión activa");
        }
        user.setSessionActive(false);
        userDao.save(user);
    }
}
