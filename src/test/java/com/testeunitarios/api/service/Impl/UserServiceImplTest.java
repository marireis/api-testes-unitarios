package com.testeunitarios.api.service.Impl;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.repository.UserRepository;
import com.testeunitarios.api.resources.exceptions.DataIntegratyViolationException;
import com.testeunitarios.api.service.excepitons.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    public static final Integer ID       = 1;
    public static final String PASSWORD1 = "123";
    public static final String EMAIL1    = "mm@email.com";
    public static final String NAME1     = "MARINA";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;


    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    private Users users;
    private UserDto userDto;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUsers);

        Users response = userServiceImpl.findById(ID);
        assertNotNull(response);
        assertEquals(Users.class,response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME1, response.getName());
        assertEquals(EMAIL1, response.getEmail());
    }

    @Test
    void whenFindByIdTheReturnAnObjectNotFoundException(){//quando o id nao for encontrado
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try{
            userServiceImpl.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllTheReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(users));
        List<Users> response = userServiceImpl.findAll();
        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME1, response.get(INDEX).getName());
        assertEquals(EMAIL1, response.get(INDEX).getEmail());
        assertEquals(PASSWORD1, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateTheReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);
        Users response = userServiceImpl.create(userDto);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME1, response.getName());
        assertEquals(EMAIL1, response.getEmail());
        assertEquals(PASSWORD1 , response.getPassword());
    }

    @Test
    void whenCreateTheReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUsers);
        try{
            optionalUsers.get().setId(2);
            userServiceImpl.create(userDto);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void whenUpdateTheReturnSuccess() {
        when(userRepository.save(any())).thenReturn(users);
        Users response = userServiceImpl.update(userDto);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME1, response.getName());
        assertEquals(EMAIL1, response.getEmail());
        assertEquals(PASSWORD1 , response.getPassword());
    }

    @Test
    void delete() {
    }

    private void startUser(){
        users = new Users(ID, NAME1, EMAIL1, PASSWORD1);
        userDto = new UserDto(ID, NAME1, EMAIL1, PASSWORD1);
        optionalUsers = Optional.of(new Users(ID, NAME1, EMAIL1, PASSWORD1));
    }
}