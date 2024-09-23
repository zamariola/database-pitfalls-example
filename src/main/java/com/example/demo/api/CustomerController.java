package com.example.demo.api;

import com.example.demo.database.CustomerEntity;
import com.example.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

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
}
