package com.testeunitarios.api.resources.controller;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    public static final Integer ID       = 1;
    public static final String PASSWORD1 = "123";
    public static final String EMAIL1    = "mm@email.com";
    public static final String NAME1     = "MARINA";

    private Users users;
    private UserDto userDto;
    @InjectMocks
    private UserController controller;

    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        users = new Users(ID, NAME1, EMAIL1, PASSWORD1);
        userDto = new UserDto(ID, NAME1, EMAIL1, PASSWORD1);

    }
}