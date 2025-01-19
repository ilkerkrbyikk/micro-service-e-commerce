package com.Ilker.payment_service.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Name is required.")
        String firstName,
        @NotNull(message = "Last name is required.")
        String lastName,
        @Email(message = "Please enter a valid e-mail.")
        @NotNull(message = "Email is required.")
        String email
) {
}
