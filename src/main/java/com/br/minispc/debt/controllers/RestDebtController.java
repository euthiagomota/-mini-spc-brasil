package com.br.minispc.debt.controllers;

import com.br.minispc.debt.dto.RequestDebtDto;
import com.br.minispc.debt.entities.DebtEntity;
import com.br.minispc.debt.services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/debts")
public class RestDebtController {

    @Autowired
    DebtService debtService;

    @PostMapping("/{customerId}/{companyId}")
    public ResponseEntity<DebtEntity> registerDivide(
            @PathVariable Long customerId,
            @PathVariable Long companyId,
            @RequestBody RequestDebtDto requestdebtDto
    ) {
        DebtEntity debt = this.debtService.registerDivide(customerId, requestdebtDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(debt);
    }

    @GetMapping("/find/customer/{customerId}")
    public ResponseEntity<List<DebtEntity>> findDebtsByCustomer(@PathVariable Long customerId) {
        List<DebtEntity> debts = this.debtService.finddebtsByCustomer(customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(debts);
    }
}
