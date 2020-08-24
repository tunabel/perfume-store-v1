package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_product")
@Getter
@Setter
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @Id
    private int id;


    @Column(name = "type_id", insertable = false, updatable = false)
    private Integer typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "scent_id", insertable = false, updatable = false)
    private Integer scentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scent_id")
    private Scent scent;

    @Column(name = "brand_id", insertable = false, updatable = false)
    private Integer brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductSku> productSkuList = new ArrayList<>();

    private String name;

    private String description;

    private int gender;

    @Column(name = "created_date")
    private Date createdDate;
}
