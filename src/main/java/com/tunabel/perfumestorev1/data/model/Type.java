package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo_product_type")
@Getter
@Setter
public class Type {

    @Column(name = "type_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Product> listProducts = new ArrayList<>();

    private String name;
}
