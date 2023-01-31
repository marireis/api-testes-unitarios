package com.testeunitarios.api.service.Impl;

import com.testeunitarios.api.model.Users;
import com.testeunitarios.api.model.dto.UserDto;
import com.testeunitarios.api.repository.UserRepository;
import com.testeunitarios.api.controller.exceptions.DataIntegratyViolationException;
import com.testeunitarios.api.service.UserService;
import com.testeunitarios.api.service.excepitons.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Users findById(Integer id){
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Users> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Users create(UserDto userDto){

        findByEmail(userDto);
        return (Users) userRepository.save(mapper.map(userDto,Users.class));
    }

    @Override
    public Users update(UserDto obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto userDto){
        Optional<Users> users = userRepository.findByEmail(userDto.getEmail());
        if(users.isPresent() && !users.get().getId().equals(userDto.getId())){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }

    }
}
