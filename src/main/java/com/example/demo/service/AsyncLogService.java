package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
@Slf4j
public class AsyncLogService {

    private final CustomerService service;

    public void run(final long id) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Transaction starting during active transaction
        executorService.execute(() -> this.service.logById(id));

        //Transaction starting after commited
        executorService.execute(() -> {
            try {
                Thread.sleep(6* 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.service.logById(id);
        });
    }
}
