package com.example.demo.database;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    //TODO: Adding locks
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CustomerEntity> findById(Long id);
}
