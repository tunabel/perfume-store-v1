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
public class ProductImageVM {
    private int id;
    private String imageURL;
}
