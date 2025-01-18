package com.Ilker.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    private int categoryId;
    private String categoryName;
    private String categoryDescription;

}
