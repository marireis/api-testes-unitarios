package com.testeunitarios.api.service.Impl;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.repository.UserRepository;
import com.testeunitarios.api.service.excepitons.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    public static final Integer ID       = 1;
    public static final String PASSWORD1 = "123";
    public static final String EMAIL1    = "mm@email.com";
    public static final String NAME1     = "MARINA";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";


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
        optionalUsers = Optional.of(new Users(ID, NAME1, EMAIL1, PASSWORD1));
    }
}