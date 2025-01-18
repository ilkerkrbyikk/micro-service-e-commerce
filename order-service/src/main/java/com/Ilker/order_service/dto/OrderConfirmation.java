package com.Ilker.order_service.dto;

import com.Ilker.order_service.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customerResponse;
    private List<PurchaseResponse> products;
}
