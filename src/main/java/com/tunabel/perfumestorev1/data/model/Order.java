package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    private int totalPrice;

    private int status;

    private String name;

    private String address;

    private String phone;

    private String email;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderSKU> orderSKUList = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_code")
    private PromoCode promoCode;
}
