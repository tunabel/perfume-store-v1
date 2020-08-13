package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_product_brand")
@Getter
@Setter
public class Brand {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private List<Product> listProducts = new ArrayList<>();

    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "created_date")
    private Date createdDate;
}
