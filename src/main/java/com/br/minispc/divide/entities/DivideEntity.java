package com.br.minispc.divide.entities;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.divide.enuns.DivideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "divides")
public class DivideEntity {

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
    Date dueDate;

    @Enumerated()
    DivideStatus status;

    public DivideEntity(CustomerEntity customer, CompanyEntity company, BigDecimal amount, DivideStatus status, Date dueDate) {
        this.customer = customer;
        this.company = company;
        this.amount = amount;
        this.status = status;
        this.dueDate = dueDate;
    }
}
