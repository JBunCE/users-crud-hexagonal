package com.jbunce.userscrudhex.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jbunce.userscrudhex.domain.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = """
        SELECT u.* FROM `users` u WHERE u.deleted_at IS NULL
    """, nativeQuery = true)
    List<User> findAll();

    @Query(value = """
        SELECT u.* FROM `users` u WHERE u.id = :id AND u.deleted_at IS NULL
    """, nativeQuery = true)
    Optional<User> findById(Long id);
    
}
