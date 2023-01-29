package com.testeunitarios.api.resources;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {


    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    UserService userService;

    @GetMapping(value = ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> listDto = userService.findAll().stream().map(x -> mapper.map(x, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<UserDto>create(@RequestBody UserDto userDto){
        Users newUserDto = (Users) userService.create(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUserDto.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto){
        userDto.setId(id);
        Users users = userService.update(userDto);
        return ResponseEntity.ok().body(mapper.map(userService.update(userDto), UserDto.class));
    }
    
    @DeleteMapping(value = ID)
    public  ResponseEntity<UserDto> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
              
    }
}
