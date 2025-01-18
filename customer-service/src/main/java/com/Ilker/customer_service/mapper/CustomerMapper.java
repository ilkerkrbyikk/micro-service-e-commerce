package com.Ilker.customer_service.mapper;

import com.Ilker.customer_service.dto.CustomerRequest;
import com.Ilker.customer_service.dto.CustomerResponse;
import com.Ilker.customer_service.entitiy.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        return customer;
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
