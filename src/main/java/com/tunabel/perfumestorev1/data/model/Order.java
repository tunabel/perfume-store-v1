package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_order")
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @Id
    private int id;

    private String guid;

    private String username;

    @Column(name = "payment_method")
    private int paymentMethod;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "total_price")
    private long totalPrice;

    private int status;

    private String name;

    private String address;

    private String phone;

    private String email;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderSku> orderSkuList = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_code")
    private PromoCode promoCode;

    public int getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getUsername() {
        return this.username;
    }

    public int getPaymentMethod() {
        return this.paymentMethod;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public long getTotalPrice() {
        return this.totalPrice;
    }

    public int getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public List<OrderSku> getOrderSkuList() {
        return this.orderSkuList;
    }

    public PromoCode getPromoCode() {
        return this.promoCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setOrderSkuList(List<OrderSku> orderSkuList) {
        this.orderSkuList = orderSkuList;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }
}
