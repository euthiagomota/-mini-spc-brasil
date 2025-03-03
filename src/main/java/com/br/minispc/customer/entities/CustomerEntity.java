package com.br.minispc.customer.entities;

import com.br.minispc.customer.enuns.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity(name = "customers")
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String cpf;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String address;

    @Transient
    BigDecimal totalDebt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CustomerStatus status = CustomerStatus.EM_DIA;

    public CustomerEntity(String cpf, String address, String name, String email) {
        this.address = address;
        this.email = email;
        this.cpf = cpf;
        this.name = name;
    }

    public CustomerEntity() {
    }
}
