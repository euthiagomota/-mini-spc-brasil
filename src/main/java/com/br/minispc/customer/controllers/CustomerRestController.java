package com.br.minispc.customer.controllers;

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

    @GetMapping("/findAll")
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
}
