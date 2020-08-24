package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo_cart")
@Getter
@Setter
public class Cart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    @Id
    private int id;

    private String guid;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartSku> cartSkuList = new ArrayList<>();
}
