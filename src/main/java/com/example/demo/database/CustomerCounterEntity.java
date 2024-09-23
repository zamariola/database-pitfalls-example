package com.example.demo.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_counter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCounterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_customer")
    private Integer total;
}
