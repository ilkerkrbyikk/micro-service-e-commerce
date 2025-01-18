package com.Ilker.order_service.dto;

import com.Ilker.order_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private int id;
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerId;

}
