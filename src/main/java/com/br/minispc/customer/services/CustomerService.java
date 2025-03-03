package com.br.minispc.customer.services;

import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.repositories.CustomerRepository;
import com.br.minispc.debit.entities.DebitEntity;
import com.br.minispc.debit.repositories.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DebitRepository debitRepository;

    public CustomerEntity registerCustomer(RequestCustomerDto requestCustomerDto) {
        CustomerEntity customer = new CustomerEntity(
                requestCustomerDto.cpf(),
                requestCustomerDto.address(),
                requestCustomerDto.name(),
                requestCustomerDto.email()
        );

        return this.customerRepository.save(customer);
    }

    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    public Optional<CustomerEntity> findCustomer(String cpf) {
        Optional<CustomerEntity> customer = this.customerRepository.findByCpf(cpf);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        return customer;
    }

    public List<CustomerEntity> listAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerEntity> findCustomerAllData(String cpf) {
        Optional<CustomerEntity> optionalCustomer = this.customerRepository.findByCpf(cpf);
        if (optionalCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        var customer = optionalCustomer.get();
        List<DebitEntity> debits = this.debitRepository.findByCustomerId(customer.getId());

        BigDecimal totalDebt = BigDecimal.ZERO;
        for (DebitEntity debit : debits) {
            totalDebt = totalDebt.add(debit.getAmount());
        }
        customer.setTotalDebt(totalDebt);

        return Optional.of(customer);
    }

    public String delete(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = this.customerRepository.findById(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        var customer = optionalCustomer.get();

        this.customerRepository.delete(customer);

        return "The customer was deleted successful";
    }

}
