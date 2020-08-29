package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;

@Entity
@Table(name = "dbo_cart_sku")
public class CartSku {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_sku_id")
    @Id
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", insertable = false, updatable = false)
    private ProductSku productSKU;

    @Column(name = "sku_id", insertable = false, updatable = false)
    private int skuId;

    private int quantity;

    public int getId() {
        return this.id;
    }

    public Cart getCart() {
        return this.cart;
    }

    public ProductSku getProductSKU() {
        return this.productSKU;
    }

    public int getSkuId() {
        return this.skuId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProductSKU(ProductSku productSKU) {
        this.productSKU = productSKU;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
