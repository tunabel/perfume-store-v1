package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dbo_cart_sku")
@Getter
@Setter
public class CartSKU {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_sku_id")
    @Id
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", insertable = false, updatable = false)
    private ProductSKU productSKU;

    @Column(name = "sku_id", insertable = false, updatable = false)
    private int skuId;

    private int quantity;
}
