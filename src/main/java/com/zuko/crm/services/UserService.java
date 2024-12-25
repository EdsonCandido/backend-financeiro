package com.zuko.crm.services;

import com.zuko.crm.dto.request.CreateUserRequestDTO;
import com.zuko.crm.dto.request.UpdateUserRequestDTO;
import com.zuko.crm.entities.UserEntity;
import com.zuko.crm.repositorys.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserEntity> findAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "user_id"));
    }

    public UserEntity findOneById(Long id){
        if(id == null) throw new RuntimeException("Dados incompletos");

        var userExist = this.userRepository.findById(id);

        if(userExist.isEmpty()) throw new RuntimeException("Usuário não encontrado");

        return userExist.get();
    }

    public UserEntity update(UpdateUserRequestDTO dto){
        if(dto == null || dto.id() == null) throw new RuntimeException("Dados incompletos");

        var userExist = this.findOneById(dto.id());

        userExist.setLogin(dto.login());
        userExist.setAdmin(dto.isAdmin());
        userExist.setActive(dto.isActive());

        if(!dto.password().equalsIgnoreCase("")) userExist.setPassword(this.encoder.encode(dto.password()));

        this.userRepository.save(userExist);

        return userExist;
    }

    public UserEntity changeActive (long id){
        var userExist = this.findOneById(id);
        userExist.setActive(!userExist.getActive());
        this.userRepository.save(userExist);
        return userExist;
    }

}
