package com.br.minispc.customer.services;

import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity registerCustomer(RequestCustomerDto requestCustomerDto) {
        CustomerEntity customer = new CustomerEntity(
                requestCustomerDto.cpf(),
                requestCustomerDto.address(),
                requestCustomerDto.name(),
                requestCustomerDto.email()
        );

        return this.customerRepository.save(customer);
    }

    public List<CustomerEntity> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).getContent();
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

}
