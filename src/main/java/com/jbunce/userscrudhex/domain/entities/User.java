package com.jbunce.userscrudhex.domain.entities;

import java.time.LocalDate;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAt;

    private LocalDate deletedAt;

    public void create(UserRequest userRequest, String encodedPassword) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = encodedPassword;
        this.createdAt = LocalDate.now();
    }

    public void update(UserRequest request, String encodedPassword) {
        if (validateDelete()) {
            throw new EntityNotFoundException("User is deleted");
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
