package com.Ilker.customer_service.service;

import com.Ilker.customer_service.dto.CustomerRequest;
import com.Ilker.customer_service.dto.CustomerResponse;
import com.Ilker.customer_service.entitiy.Customer;
import com.Ilker.customer_service.exception.CustomerNotFoundException;
import com.Ilker.customer_service.mapper.CustomerMapper;
import com.Ilker.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request){
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format(
                        "Cannot update customer:: No customer found with given ID:: %s", request.id())));
        mergerCustomer(customer,request);
        customerRepository.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }

        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }

        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }

        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }


    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with the provided ID:: %s",customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
