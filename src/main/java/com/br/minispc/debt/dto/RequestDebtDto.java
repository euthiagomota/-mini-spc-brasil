package com.br.minispc.debt.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestDebtDto(
        BigDecimal amount,
        String companyCnpj,
        LocalDate date
) {}