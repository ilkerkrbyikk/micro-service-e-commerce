package com.Ilker.order_service.mapper;

import com.Ilker.order_service.dto.OrderRequest;
import com.Ilker.order_service.dto.OrderResponse;
import com.Ilker.order_service.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .id(request.getId())
                .reference(request.getReference())
                .paymentMethod(request.getPaymentMethod())
                .customerId(request.getCustomerId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
