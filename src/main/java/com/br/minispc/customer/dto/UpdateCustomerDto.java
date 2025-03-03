package com.br.minispc.customer.dto;

import jakarta.validation.constraints.Email;

public record UpdateCustomerDto(

        String name,

        @Email(message = "E-mail inválido")
        String email,

        String address
) {
}
