package com.Ilker.product_service.service;

import com.Ilker.product_service.dto.ProductPurchaseRequest;
import com.Ilker.product_service.dto.ProductPurchaseResponse;
import com.Ilker.product_service.dto.ProductRequest;
import com.Ilker.product_service.dto.ProductResponse;
import com.Ilker.product_service.exception.ProductPurchaseException;
import com.Ilker.product_service.mapper.ProductMapper;
import com.Ilker.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exits.");
        }

        var storesRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storesRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.getQuantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + productRequest.getQuantity());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(int productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
