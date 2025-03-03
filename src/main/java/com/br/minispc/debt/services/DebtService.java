package com.br.minispc.debt.services;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.company.repositories.CompanyRepository;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.enuns.CustomerStatus;
import com.br.minispc.customer.repositories.CustomerRepository;
import com.br.minispc.debt.dto.RequestDebtDto;
import com.br.minispc.debt.entities.DebtEntity;
import com.br.minispc.debt.enuns.DebtStatus;
import com.br.minispc.debt.repositories.DebtRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtService {
    @Autowired
    DebtRepository debtRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    public DebtEntity registerDivide(Long customerId, RequestDebtDto debt) {

        CustomerEntity customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        CompanyEntity company = this.companyRepository.findByCnpj(debt.companyCnpj())
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + debt));

        DebtEntity newDivide = new DebtEntity(
                customer,
                company,
                debt.amount(),
                DebtStatus.NAO_PAGA,
                debt.date()
        );

        customer.setStatus(CustomerStatus.PENDENTE);
        customerRepository.save(customer);

        return this.debtRepository.save(newDivide);
    }

    public List<DebtEntity> finddebtsByCustomer(Long customerId) {
        return this.debtRepository.findByCustomerId(customerId);
    }

    public List<DebtEntity> findAll() {

        return this.debtRepository.findAll();
    }
}
