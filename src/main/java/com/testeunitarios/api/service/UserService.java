package com.testeunitarios.api.service;

import com.testeunitarios.api.model.User;

public interface UserService {
    User findById(Integer id);
}
