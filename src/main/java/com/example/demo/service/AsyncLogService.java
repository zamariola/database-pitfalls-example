package com.example.demo.service;

import com.example.demo.database.CustomerEntity;
import com.example.demo.database.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
@Slf4j
public class AsyncLogService {

    private final CustomerEntityRepository repository;

    //TODO: Adding transaction
    @Transactional
    public void run(final long id) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            while(true) {
                Optional<CustomerEntity> entity = this.repository.findById(id);
                log.info("Current state for entity {} is {}", id, entity);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
