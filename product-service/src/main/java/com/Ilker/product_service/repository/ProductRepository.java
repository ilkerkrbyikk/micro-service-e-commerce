package com.Ilker.product_service.repository;

import com.Ilker.product_service.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
