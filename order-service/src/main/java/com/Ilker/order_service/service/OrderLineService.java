package com.Ilker.order_service.service;

import com.Ilker.order_service.dto.OrderLineRequest;
import com.Ilker.order_service.dto.OrderLineResponse;
import com.Ilker.order_service.mapper.OrderLineMapper;
import com.Ilker.order_service.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
