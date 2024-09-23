package com.example.demo.service;

import com.example.demo.database.CustomerEntity;
import com.example.demo.database.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerEntityRepository repository;

    private final CustomerTotalService customerTotalService;

    @Transactional
    public CustomerEntity save(CustomerEntity customer) {
        final var entity = this.repository.save(customer);

        //TODO: add try catch to avoid stopping the transaction
        try {
            this.customerTotalService.calculateTotal();
        } catch (Exception ex) {
            log.error("Error while calculating totals", ex);
        }

        return entity;
    }

    public List<CustomerEntity> findAll() {
        return this.repository.findAll();
    }

}
