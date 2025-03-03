package com.br.minispc.debit.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


public record RequestDebitDto(
        BigDecimal amount,
        String companyCnpj,
        LocalDate date
) {}
