package com.Ilker.payment_service.service;

import com.Ilker.payment_service.dto.PaymentNotificationRequest;
import com.Ilker.payment_service.dto.PaymentRequest;
import com.Ilker.payment_service.mapper.PaymentMapper;
import com.Ilker.payment_service.notification.NotificationProducer;
import com.Ilker.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer producer;


    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));

        producer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().firstName(),
                        request.getCustomer().lastName(),
                        request.getCustomer().email()
                )
        );
        return payment.getId();
    }
}
