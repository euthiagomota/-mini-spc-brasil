package com.br.minispc.domain.divide;

import com.br.minispc.domain.company.CompanyEntity;
import com.br.minispc.domain.customer.CustomerEntity;
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
public class DividesEntity {

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

    @Column
    String status;
}
