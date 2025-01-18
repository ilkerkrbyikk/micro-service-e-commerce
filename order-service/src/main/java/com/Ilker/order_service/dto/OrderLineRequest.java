package com.Ilker.order_service.dto;

public record OrderLineRequest(Integer id, Integer orderId,
                               Integer productId,
                               double quantity) {
}
