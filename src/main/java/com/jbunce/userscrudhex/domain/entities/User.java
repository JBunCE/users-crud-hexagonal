package com.jbunce.userscrudhex.domain.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;
import com.jbunce.userscrudhex.infrastructure.exceptions.EntityNotFoundException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "users")
public class User {
    
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private LocalDate createdAt;

    private LocalDate deletedAt;

    public void create(UserRequest userRequest, String encodedPassword) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = encodedPassword;
        this.createdAt = LocalDate.now();
        this.deletedAt = null;
    }

    public void update(UserRequest request, String encodedPassword) {
        if (validateDelete()) {
            throw new EntityNotFoundException();
        }

        this.name = request.getName();
        this.email = request.getEmail();
        this.password = encodedPassword;
    }

    public void delete() {
        this.deletedAt = LocalDate.now();
    }

    public Boolean validateDelete() {
        return this.deletedAt != null;
    }

}
