package com.br.minispc.customer.controllers;

import com.br.minispc.customer.dto.UpdateCustomerDto;
import com.br.minispc.customer.services.CustomerService;
import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/customers")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerEntity> registerCustomer(
            @Valid @RequestBody RequestCustomerDto requestCustomerDto) {
        CustomerEntity customer = this.customerService.registerCustomer(requestCustomerDto);
        System.out.println("Valor do customer: " + customer);
        return ResponseEntity.status(201).body(customer);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerEntity>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).ascending());
        Page<CustomerEntity> customers = customerService.findAll(pageable);
        return ResponseEntity.ok(customers.getContent());
    }

    @GetMapping("/find/{cpf}")
    public ResponseEntity<Optional<CustomerEntity>> findCustomer(@PathVariable String cpf) {
        Optional<CustomerEntity> customer = this.customerService.findCustomer(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long customerId) {
        Boolean customerDeleted = this.customerService.deleteCustomer(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(customerDeleted);
    }

    @PatchMapping("/update/{customerId}")
    public ResponseEntity<Boolean> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody UpdateCustomerDto dto
            ) {
        this.customerService.updateCustomer(customerId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
