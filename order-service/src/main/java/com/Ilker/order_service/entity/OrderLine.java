package com.Ilker.order_service.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int productId;
    private double quantity;
}
