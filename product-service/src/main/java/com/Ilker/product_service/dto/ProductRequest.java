package com.Ilker.product_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private Integer id;
    @NotNull(message = "Product name is required.")
    private String name;
    @NotNull(message = "Product description is required.")
    private String description;
    @Positive(message = "Quantity can not be mines.")
    private double availableQuantity;
    @NotNull(message = "Price should be positive.")
    private BigDecimal price;
    @NotNull(message = "Product category is required.")
    private int categoryId;

}
