package com.Ilker.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
