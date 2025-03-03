package com.br.minispc.debit.entities;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.debit.enuns.DebitStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "debts")
public class DebitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    CompanyEntity company;

    @Column(precision = 19, scale = 2)
    BigDecimal amount;

    @Column
    LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    DebitStatus status;

    public DebitEntity(CustomerEntity customer, CompanyEntity company, BigDecimal amount, DebitStatus status, LocalDate dueDate) {
        this.customer = customer;
        this.company = company;
        this.amount = amount;
        this.status = status;
        this.dueDate = dueDate;
    }
}
