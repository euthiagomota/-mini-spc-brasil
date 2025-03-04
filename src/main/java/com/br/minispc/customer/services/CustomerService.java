package com.br.minispc.customer.services;

import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.dto.UpdateCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.repositories.CustomerRepository;
import com.br.minispc.debt.entities.DebtEntity;
import com.br.minispc.debt.repositories.DebtRepository;
import jakarta.transaction.Transactional;
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
    DebtRepository debtRepository;

    public CustomerEntity registerCustomer(RequestCustomerDto requestCustomerDto) {

        if (this.customerRepository.findByCpf(requestCustomerDto.cpf()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Customer already exists with this CPF.");
        }

        if (this.customerRepository.findByEmail(requestCustomerDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Customer already exists with this email.");
        }

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

    public Optional<CustomerEntity> findCustomerAllData(String cpf) {
        Optional<CustomerEntity> optionalCustomer = this.customerRepository.findByCpf(cpf);
        if (optionalCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        var customer = optionalCustomer.get();
        List<DebtEntity> debits = this.debtRepository.findByCustomerId(customer.getId());

        BigDecimal totalDebt = BigDecimal.ZERO;

        for (DebtEntity debit : debits) {
            totalDebt = totalDebt.add(debit.getAmount());
        }

        customer.setTotalDebt(totalDebt);

        return Optional.of(customer);
    }

    public CustomerEntity findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Transactional
    public Boolean deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            return false;
        }
        debtRepository.deleteByCustomerId(id);
        customerRepository.deleteById(id);

        return true;
    }

    @Transactional
    public void updateCustomer(Long customerId, UpdateCustomerDto dto) {
        Optional<CustomerEntity> existingCustomerOpt = customerRepository.findById(customerId);

        if (existingCustomerOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
        }

        CustomerEntity existingCustomer = existingCustomerOpt.get();

        if (dto.name() != null) existingCustomer.setName(dto.name());
        if (dto.email() != null) existingCustomer.setEmail(dto.email());
        if (dto.address() != null) existingCustomer.setAddress(dto.address());

        customerRepository.save(existingCustomer);

    }

}
