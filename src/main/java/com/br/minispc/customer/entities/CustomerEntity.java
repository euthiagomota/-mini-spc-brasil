package com.br.minispc.customer.entities;

import com.br.minispc.customer.enuns.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CustomerStatus status = CustomerStatus.PAID;

    public CustomerEntity(String cpf, String address, String name, String email) {
        this.address = address;
        this.email = email;
        this.cpf = cpf;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public CustomerEntity() {
    }
}
