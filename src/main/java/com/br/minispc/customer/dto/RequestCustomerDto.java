package com.br.minispc.customer.dto;

public record RequestCustomerDto(
        String cpf,
        String name,
        String email,
        String address
) {}
