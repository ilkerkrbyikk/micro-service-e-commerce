package com.Ilker.customer_service.repository;

import com.Ilker.customer_service.entitiy.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
