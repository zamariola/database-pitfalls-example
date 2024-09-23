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

    public CustomerEntity save(CustomerEntity customer) {
        return this.repository.save(customer);
    }

    public List<CustomerEntity> findAll() {
        return this.repository.findAll();
    }

}
