package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dbo_order_sku")
public class OrderSku {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_sku_id")
    @Id
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id")
    private ProductSku productSKU;

    private int quantity;

    private int price;
}
