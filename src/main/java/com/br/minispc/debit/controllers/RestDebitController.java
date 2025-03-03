package com.br.minispc.debit.controllers;

import com.br.minispc.debit.dto.RequestDebitDto;
import com.br.minispc.debit.entities.DebitEntity;
import com.br.minispc.debit.services.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/debits")
public class RestDebitController {

    @Autowired
    DebitService debitService;

    @PostMapping("/{customerId}/{companyId}")
    public ResponseEntity<DebitEntity> registerDivide(
            @PathVariable Long customerId,
            @PathVariable Long companyId,
            @RequestBody RequestDebitDto requestDebitDto
    ) {
        DebitEntity debit = this.debitService.registerDivide(customerId, requestDebitDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(debit);
    }

    @GetMapping("/find/customer/{customerId}")
    public ResponseEntity<List<DebitEntity>> findDebitsByCustomer(@PathVariable Long customerId) {
        List<DebitEntity> debits = this.debitService.findDebitsByCustomer(customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(debits);
    }
}
