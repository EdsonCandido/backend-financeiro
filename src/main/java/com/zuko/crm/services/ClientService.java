package com.zuko.crm.services;

import com.zuko.crm.entities.ClientEntity;
import com.zuko.crm.repositorys.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ClientEntity findOne(long id){
        var exist = this.clientRepository.findById(id);

        if(exist.isEmpty()) throw new RuntimeException("Não encontrado");

        return exist.get();
    }
    public ClientEntity store(){

    }

}
