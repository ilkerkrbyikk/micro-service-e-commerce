package com.Ilker.customer_service.dto;

import com.Ilker.customer_service.entitiy.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record CustomerRequest(

        String id,
        @NotNull(message = "Customer firstname is required.")
        String firstName,
        @NotNull(message = "Customer lastname is required.")
        String lastName,
        @NotNull(message = "Customer email is required.")
                @Email(message = "Customer e-mail is not a valid e-mail address.")
        String email,
        Address address) {
}
