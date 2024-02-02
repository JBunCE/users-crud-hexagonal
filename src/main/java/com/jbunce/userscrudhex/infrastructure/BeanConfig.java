package com.jbunce.userscrudhex.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jbunce.userscrudhex.domain.services.IUserService;
import com.jbunce.userscrudhex.domain.services.implementations.UserServiceImpl;
import com.jbunce.userscrudhex.infrastructure.repositories.IUserMongoRepository;

@Configuration
public class BeanConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserMongoRepository userRepository;
    
    @Bean
    public IUserService userServiceImpl() {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

}
