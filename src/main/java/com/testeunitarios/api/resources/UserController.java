package com.testeunitarios.api.resources;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> listDto = userService.findAll().stream().map(x -> mapper.map(x, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
