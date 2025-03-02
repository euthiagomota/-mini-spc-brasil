package com.br.minispc.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String cnpj;

    @Column()
    String social_reason;

    @Column()
    String email;

    public CompanyEntity(String cnpj, String social_reason, String email) {
        this.cnpj = cnpj;
        this.social_reason = social_reason;
        this.email = email;
    }
}
