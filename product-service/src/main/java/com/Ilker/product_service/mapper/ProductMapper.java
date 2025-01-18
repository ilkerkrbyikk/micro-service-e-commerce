package com.Ilker.product_service.mapper;

import com.Ilker.product_service.dto.ProductPurchaseResponse;
import com.Ilker.product_service.dto.ProductRequest;
import com.Ilker.product_service.dto.ProductResponse;
import com.Ilker.product_service.entitiy.Category;
import com.Ilker.product_service.entitiy.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .availableQuantity(request.getAvailableQuantity())
                .price(request.getPrice())
                .category(
                        Category.builder()
                                .id(request.getCategoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
