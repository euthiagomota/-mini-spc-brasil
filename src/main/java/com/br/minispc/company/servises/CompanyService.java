package com.br.minispc.company.servises;

import com.br.minispc.company.dto.RequestCompanyDto;
import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyEntity registerCompany(RequestCompanyDto requestCompanyDto) {
        CompanyEntity company = new CompanyEntity(
                requestCompanyDto.cnpj(),
                requestCompanyDto.social_reason(),
                requestCompanyDto.email()
        );
        return this.companyRepository.save(company);
    }
}
