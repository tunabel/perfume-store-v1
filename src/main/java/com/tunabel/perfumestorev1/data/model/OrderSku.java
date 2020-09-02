package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;

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

    public int getId() {
        return this.id;
    }

    public Order getOrder() {
        return this.order;
    }

    public ProductSku getProductSKU() {
        return this.productSKU;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProductSKU(ProductSku productSKU) {
        this.productSKU = productSKU;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
