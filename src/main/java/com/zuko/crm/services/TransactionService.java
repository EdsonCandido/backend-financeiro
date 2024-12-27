package com.zuko.crm.services;

import com.zuko.crm.dto.request.StoreTransactionRequestDTO;
import com.zuko.crm.dto.response.TransactionItem;
import com.zuko.crm.dto.response.TransactionListResponseDTO;
import com.zuko.crm.entities.TransactionEntity;
import com.zuko.crm.repositorys.CategoryRepository;
import com.zuko.crm.repositorys.TransactionRepository;
import com.zuko.crm.repositorys.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionListResponseDTO findAll(int page, int pageSize){
        var elements = transactionRepository.findByIsActiveTrue(
                PageRequest.of(page, pageSize, Sort.Direction.DESC, "created_at"))
                .map(el -> new TransactionItem(
                        el.getAccountId(),
                        el.getType(),
                        el.getDescription(),
                        el.getAmount(),
                        el.getCreatedAt(),
                        el.getUpdatedAt(),
                        el.getTransactionDate(),
                        el.getUser(),
                        el.getCategory(),
                        el.getStatusTransaction()
                        ));

        return new TransactionListResponseDTO(
                elements.getContent(), page, pageSize,
                elements.getTotalPages(), (int) elements.getTotalElements());
    }

    public TransactionEntity findOneById(Long id){
        if (id == null) throw new RuntimeException("Dados incompletos");
        var exist = this.transactionRepository.findById(id);
        if (exist.isEmpty()) throw new RuntimeException("Não encontrado");
        return exist.get();
    }
    public TransactionEntity store(StoreTransactionRequestDTO dto, JwtAuthenticationToken token){

        if(dto == null) throw  new RuntimeException("Dados incompletos");

        var user = userRepository.findById(Long.parseLong(token.getName()));
        if(user.isEmpty()) throw new RuntimeException("Usuário invalido");

        var category = this.categoryRepository.findById((dto.categoryId()));
        if(category.isEmpty()) throw new RuntimeException("Categoria invalido");


        TransactionEntity transaction = null;

        if(dto.accountId()!= null){
            transaction = this.findOneById(dto.accountId());
            transaction.setUpdatedAt(LocalDateTime.now());
        }else{
            transaction = new TransactionEntity();
        }

        transaction.setUser(user.get());
        transaction.setCategory(category.get());
        transaction.setTransactionDate(dto.transactionDate());


        this.transactionRepository.save(transaction);

        return transaction;
    }

}
