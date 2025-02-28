package com.br.minispc.domain.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String cpf;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String contact;

    @Column(nullable = false)
    String address;

    @Enumerated(EnumType.STRING)
    CustomerStatus status;
}
