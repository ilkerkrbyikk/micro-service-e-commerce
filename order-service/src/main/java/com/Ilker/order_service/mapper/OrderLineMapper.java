package com.Ilker.order_service.mapper;

import com.Ilker.order_service.dto.OrderLineRequest;
import com.Ilker.order_service.dto.OrderLineResponse;
import com.Ilker.order_service.entity.Order;
import com.Ilker.order_service.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
