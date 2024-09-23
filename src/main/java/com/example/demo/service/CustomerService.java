package com.example.demo.service;

import com.example.demo.database.CustomerEntity;
import com.example.demo.database.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerService {

    private final CustomerEntityRepository repository;

    private final CustomerTotalService customerTotalService;

    public CustomerEntity save(CustomerEntity customer) {
        final var entity = this.repository.save(customer);

        this.customerTotalService.calculateTotal();

        return entity;
    }

    public List<CustomerEntity> findAll() {
        return this.repository.findAll();
    }

}
