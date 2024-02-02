package com.jbunce.userscrudhex.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import com.jbunce.userscrudhex.domain.entities.User;

public interface IUserRepository {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User entity);

    void delete(User entity);

}
