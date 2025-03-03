package com.br.minispc.company.controllers;

import com.br.minispc.company.dto.RequestCompanyDto;
import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.company.servises.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<CompanyEntity> registerCompany(
            @RequestBody RequestCompanyDto requestCompanyDto) {
        CompanyEntity company = this.companyService.registerCompany(requestCompanyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> findAll() {
        List<CompanyEntity> companies = this.companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }
}
