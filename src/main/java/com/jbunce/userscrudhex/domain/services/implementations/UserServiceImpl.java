package com.jbunce.userscrudhex.domain.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;
import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;
import com.jbunce.userscrudhex.application.dtos.response.UserReponse;
import com.jbunce.userscrudhex.domain.entities.User;
import com.jbunce.userscrudhex.domain.repositories.IUserRepository;
import com.jbunce.userscrudhex.domain.services.IUserService;
import com.jbunce.userscrudhex.domain.utilities.TimeUtils;

import jakarta.persistence.EntityNotFoundException;

public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BaseResponse findAll() {
        List<User> users = userRepository.findAll();
        
        List<UserReponse> userReponse = users.stream().map(this::toUserReponse).collect(Collectors.toList());

        return BaseResponse.builder()
            .data(userReponse)
            .message("Users found")
            .success(true)
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return BaseResponse.builder()
            .data(toUserReponse(user))
            .message("User found")
            .success(true)
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse create(UserRequest userRequest) {
        User user = new User();
        user.create(userRequest, passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);

        return BaseResponse.builder()
            .data(toUserReponse(user))
            .message("User created")
            .success(true)
            .status(HttpStatus.CREATED)
            .statusCode(HttpStatus.CREATED.value()).build();
    }

    @Override
    public BaseResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.update(userRequest, passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);

        return BaseResponse.builder()
            .data(toUserReponse(user))
            .message("User updated")
            .success(true)
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.delete();
        userRepository.save(user);

        return BaseResponse.builder()
            .data(toUserReponse(user))
            .message("User deleted")
            .success(true)
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value()).build();
    }

    private UserReponse toUserReponse(User user) {
        UserReponse userReponse = new UserReponse();

        userReponse.setId(user.getId());
        userReponse.setName(user.getName());
        userReponse.setEmail(user.getEmail());
        userReponse.setCreatedAt(TimeUtils.toString(user.getCreatedAt()));

        return userReponse;
    }
}
