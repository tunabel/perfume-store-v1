package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "dbo_product_image")
@Getter
@Setter
public class ProductImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    @Id
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date")
    private Date createdDate;

}
