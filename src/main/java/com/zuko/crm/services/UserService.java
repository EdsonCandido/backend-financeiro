package com.zuko.crm.services;

import com.zuko.crm.dto.request.CreateUserRequestDTO;
import com.zuko.crm.entities.UserEntity;
import com.zuko.crm.repositorys.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserEntity createUser(CreateUserRequestDTO dto){

        var userExist = userRepository.findByLogin(dto.login());

        if(userExist.isPresent()) throw new RuntimeException("Login já existe");

        var newUser = new UserEntity();

        newUser.setActive(dto.isActive());
        newUser.setAdmin(dto.isAdmin());
        newUser.setLogin(dto.login());
        newUser.setUsername(dto.username());
        newUser.setPassword(this.encoder.encode(dto.password()));

        return this.userRepository.save(newUser);
    }
}
