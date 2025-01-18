package com.Ilker.product_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseResponse {

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;


}
