package com.example.demo.service;

import com.example.demo.database.CustomerCounterEntity;
import com.example.demo.database.CustomerCounterRepository;
import com.example.demo.database.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerTotalService {

    private final CustomerEntityRepository customerRepository;
    private final CustomerCounterRepository customerCounterRepository;

    public int calculateTotal() {

        int count = (int) customerRepository.count();
        final var counter = CustomerCounterEntity.builder().total(count).build();

        this.customerCounterRepository.deleteAll();
        this.customerCounterRepository.save(counter);
        return count;
    }
}
