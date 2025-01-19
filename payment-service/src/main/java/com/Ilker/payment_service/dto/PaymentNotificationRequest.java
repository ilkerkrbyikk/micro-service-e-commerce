package com.Ilker.payment_service.dto;

import com.Ilker.payment_service.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerLastName,
        String customerEmail
) {
}
