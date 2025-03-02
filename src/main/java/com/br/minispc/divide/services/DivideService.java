package com.br.minispc.divide.services;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.company.repositories.CompanyRepository;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.enuns.CustomerStatus;
import com.br.minispc.customer.repositories.CustomerRepository;
import com.br.minispc.divide.dto.RequestDivideDto;
import com.br.minispc.divide.entities.DivideEntity;
import com.br.minispc.divide.enuns.DivideStatus;
import com.br.minispc.divide.repositories.DivideRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DivideService {
    @Autowired
    DivideRepository divideRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    public DivideEntity registerDivide(Long customerId, Long companyId, RequestDivideDto divide) {

        LocalDate localDate = LocalDate.now().plusDays(7);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        CustomerEntity customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        CompanyEntity company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + divide));

        DivideEntity newDivide = new DivideEntity(
                customer,
                company,
                divide.amount(),
                DivideStatus.UNPAID,
                date
        );

        customer.setStatus(CustomerStatus.UNPAID);
        customerRepository.save(customer);

        return this.divideRepository.save(newDivide);
    }
}
