package com.tunabel.perfumestorev1.model.viewmodel.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSKUVM {
    private int id;
    private String name;
    private String spec;
    private int mainSku;
    private int price;
    private int quantity;
    private int volume;
    private String imageURL;
    private int status;
    private LocalDate createdDate;
}
