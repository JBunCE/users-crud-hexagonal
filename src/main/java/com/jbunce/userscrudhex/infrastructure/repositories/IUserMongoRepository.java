package com.jbunce.userscrudhex.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jbunce.userscrudhex.domain.entities.User;

@Primary
@Repository
public interface IUserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);
    List<User> findAll();
}
