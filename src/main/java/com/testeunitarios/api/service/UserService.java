package com.testeunitarios.api.service;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDto userDto);

    Users update(UserDto userDto);
}
