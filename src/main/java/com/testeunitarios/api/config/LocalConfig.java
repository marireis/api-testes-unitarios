package com.testeunitarios.api.config;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@ConfigurationProperties
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;
    @Bean
    public void startDB(){
        Users users = new Users(1,"mari","mari@email.com","123");
        Users users2 = new Users(2,"Luiz","lulu@email.com","123");

        userRepository.saveAll(List.of(users, users2));
    }
}
