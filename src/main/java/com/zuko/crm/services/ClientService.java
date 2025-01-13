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

    }
    public ClientEntity store(){

    }

}
