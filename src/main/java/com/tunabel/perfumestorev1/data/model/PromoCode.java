package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_promo_code")
@Getter
@Setter
public class PromoCode {

    @Column(name = "promo_code")
    @Id
    private String promoCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promoCode")
    private List<Order> orderList = new ArrayList<>();

    private float discount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "created_date")
    private Date createdDate;
}
