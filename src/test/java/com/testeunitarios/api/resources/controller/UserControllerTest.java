package com.testeunitarios.api.resources.controller;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
    public static final Integer ID       = 1;
    public static final String PASSWORD1 = "123";
    public static final String EMAIL1    = "mm@email.com";
    public static final String NAME1     = "MARINA";
    public static final int INDEX = 0;
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
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDto);
        ResponseEntity<UserDto> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME1, response.getBody().getName());
        assertEquals(EMAIL1, response.getBody().getEmail());
        assertEquals(PASSWORD1, response.getBody().getPassword());

    }

    @Test
    void whenFindAllTheReturnAnListUserDTO() {
        when(service.findAll()).thenReturn(List.of(users));
        when(mapper.map(any(), any())).thenReturn(userDto);
        ResponseEntity<List<UserDto>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME1, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL1, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD1, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void whenCreatethenReturnCreated() {
        when(service.create(any())).thenReturn(users);
        ResponseEntity<UserDto> response = controller.create(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
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