package com.br.minispc.company.dto;

import jakarta.persistence.Column;

public record RequestCompanyDto(
        String cnpj,
        String social_reason,
        String email
) {}
