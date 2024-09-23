package com.example.demo.service;

import com.example.demo.database.CustomerEntity;
import com.example.demo.database.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerEntityRepository repository;

    private final CustomerTotalService customerTotalService;

    @Transactional
    public CustomerEntity save(CustomerEntity customer) {
        final var entity = this.repository.save(customer);

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

    public long count() {
        return this.repository.count();
    }

    public CustomerEntity update(Long id, CustomerEntity customer) throws InterruptedException {
        CustomerEntity customerEntity = this.repository.findById(id).orElseThrow();
        customerEntity.setAge(customer.getAge());
        customerEntity.setName(customer.getName());
        final var entity = this.repository.save(customerEntity);

        log.info("Updated {} with {}", id, customer);

        Thread.sleep(60 * 1000L);
        return entity;
    }

}
