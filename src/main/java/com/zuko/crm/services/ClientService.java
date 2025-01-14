package com.zuko.crm.services;

import com.zuko.crm.dto.request.CreateClientRequestDTO;
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
    public ClientEntity store(CreateClientRequestDTO dto){
        if(dto == null) throw  new RuntimeException("Dados incompletos");

        ClientEntity client = null;

        if(dto.clientId()!= null){
            client = this.findOne(dto.clientId());
        }else{
            client = new ClientEntity();
        }

        client.setCorporateReason(dto.corporateReason());
        client.setFantasyName(dto.fantasyName());

        this.clientRepository.save(client);

        return client;

    }

}
