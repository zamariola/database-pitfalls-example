package com.example.demo.api;

import com.example.demo.database.CustomerEntity;
import com.example.demo.service.AsyncLogService;
import com.example.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    private final AsyncLogService asyncLogService;


    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll() {
        final var entities = this.customerService.findAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customer) {

        final var entity = this.customerService.save(customer);
        return ResponseEntity.ok(entity);
    }

    @PutMapping(path = "/{id}", consumes = {"application/json"})
    public ResponseEntity<CustomerEntity> update(
            @PathVariable("id") long id,
            @RequestBody CustomerEntity customer) throws InterruptedException {

        this.asyncLogService.run(id);

        CustomerEntity entity = this.customerService.update(id, customer);

        return ResponseEntity.ok(entity);
    }
}
