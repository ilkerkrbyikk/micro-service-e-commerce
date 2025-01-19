package com.Ilker.notification_service.dto;

import com.Ilker.notification_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record PaymentConfirmation( String orderReference,
                                   BigDecimal amount,
                                   PaymentMethod paymentMethod,
                                   String customerName,
                                   String customerLastName,
                                   String customerEmail) {

}
