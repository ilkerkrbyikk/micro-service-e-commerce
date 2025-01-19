package com.Ilker.order_service.service;

import com.Ilker.order_service.client.CustomerClient;
import com.Ilker.order_service.client.PaymentClient;
import com.Ilker.order_service.client.ProductClient;
import com.Ilker.order_service.dto.*;
import com.Ilker.order_service.exception.BusinessException;
import com.Ilker.order_service.kafka.OrderProducer;
import com.Ilker.order_service.mapper.OrderMapper;
import com.Ilker.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder( OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() ->
                        new BusinessException("Can not create order:: No customer exists with the provided ID: "
                                + request.getCustomerId()));

        var purchaseProducts = this.productClient.purchaseProducts(request.getProducts());

        var order = this.orderRepository.save(mapper.toOrder(request));
        for(PurchaseRequest purchaseRequest: request.getProducts()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity())
            );

            var paymentRequest = new PaymentRequest(
                    request.getAmount(),
                    request.getPaymentMethod(),
                    order.getId(),
                    order.getReference(),
                    customer
            );
            paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(),
                        request.getAmount(),
                        request.getPaymentMethod()
                        ,customer,
                        purchaseProducts)
        );
        }
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(int orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() ->
                        new EntityNotFoundException
                                (String.format("No order found with the provided ID: %d" + orderId)));
    }
}
