package com.jbunce.userscrudhex.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbunce.userscrudhex.application.dtos.request.UserRequest;
import com.jbunce.userscrudhex.application.dtos.response.BaseResponse;
import com.jbunce.userscrudhex.domain.services.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAll() {
        return userService.findAll().toResponseEntity();
    }

    @GetMapping("{userId}")
    public ResponseEntity<BaseResponse> getMethodName(@PathVariable String userId) {
        return userService.findById(userId).toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody UserRequest request) {
        return userService.create(request).toResponseEntity();
    }

    @PutMapping("{userId}")
    public ResponseEntity<BaseResponse> update(@PathVariable String userId, @RequestBody UserRequest request) {
        return userService.update(userId, request).toResponseEntity();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String userId) {
        return userService.delete(userId).toResponseEntity();
    }

}
