package com.agr.user.model.dao;

import com.agr.user.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdPerson(Long idPerson);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
