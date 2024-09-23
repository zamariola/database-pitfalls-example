package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCounterRepository extends JpaRepository<CustomerCounterEntity, Long> {
}
