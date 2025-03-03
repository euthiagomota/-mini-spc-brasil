package com.br.minispc.debit.services;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.company.repositories.CompanyRepository;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.enuns.CustomerStatus;
import com.br.minispc.customer.repositories.CustomerRepository;
import com.br.minispc.debit.dto.RequestDebitDto;
import com.br.minispc.debit.entities.DebitEntity;
import com.br.minispc.debit.enuns.DebitStatus;
import com.br.minispc.debit.repositories.DebitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class DebitService {
    @Autowired
    DebitRepository debitRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    public DebitEntity registerDivide(Long customerId, RequestDebitDto debit) {

        CustomerEntity customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        CompanyEntity company = this.companyRepository.findByCnpj(debit.companyCnpj())
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + debit));

        DebitEntity newDivide = new DebitEntity(
                customer,
                company,
                debit.amount(),
                DebitStatus.NAO_PAGA,
                debit.date()
        );

        customer.setStatus(CustomerStatus.PENDENTE);
        customerRepository.save(customer);

        return this.debitRepository.save(newDivide);
    }

    public List<DebitEntity> findDebitsByCustomer(Long customerId) {
        return this.debitRepository.findByCustomerId(customerId);
    }

    public List<DebitEntity> findAll() {
        return this.debitRepository.findAll();
    }
}
