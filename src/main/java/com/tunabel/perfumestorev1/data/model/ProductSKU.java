package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "dbo_product_sku")
@Getter
@Setter
public class ProductSKU {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sku_id")
    @Id
    private int id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(name = "main_sku")
    private int mainSku;

    private int price;
    private int quantity;
    private int volume;

    @Column(name = "image_url")
    private String imageURL;

    private int status;

    @Column(name = "created_date")
    private Date createdDate;
}
