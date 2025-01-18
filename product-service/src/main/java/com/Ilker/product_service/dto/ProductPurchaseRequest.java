package com.Ilker.product_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPurchaseRequest {

    @NotNull(message = "Product is mandatory.")
    private int productId;
    @NotNull(message = "Quantity is mandatory.")
    private double quantity;
}
