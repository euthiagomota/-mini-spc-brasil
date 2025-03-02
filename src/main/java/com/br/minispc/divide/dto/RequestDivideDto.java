package com.br.minispc.divide.dto;

import com.br.minispc.company.entities.CompanyEntity;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.divide.enuns.DivideStatus;

import java.math.BigDecimal;
import java.util.Date;

public record RequestDivideDto(
        BigDecimal amount
) {}
