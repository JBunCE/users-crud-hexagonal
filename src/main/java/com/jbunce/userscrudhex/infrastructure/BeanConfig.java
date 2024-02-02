package com.jbunce.userscrudhex.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jbunce.userscrudhex.domain.repositories.IUserRepository;
import com.jbunce.userscrudhex.domain.services.IUserService;
import com.jbunce.userscrudhex.domain.services.implementations.UserServiceImpl;

@Configuration
public class BeanConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public IUserService userServiceImpl(IUserRepository userRepository) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

}
