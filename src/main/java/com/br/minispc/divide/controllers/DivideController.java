package com.br.minispc.divide.controllers;

import com.br.minispc.divide.dto.RequestDivideDto;
import com.br.minispc.divide.entities.DivideEntity;
import com.br.minispc.divide.services.DivideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/divides")
public class DivideController {

    @Autowired
    DivideService divideService;

    @PostMapping("/{customerId}/{companyId}")
    public ResponseEntity<DivideEntity> registerDivide(
            @PathVariable Long customerId,
            @PathVariable Long companyId,
            @RequestBody RequestDivideDto requestDivideDto
    ) {
        DivideEntity divide = this.divideService.registerDivide(customerId, companyId, requestDivideDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(divide);
    }
}
