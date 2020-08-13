package com.tunabel.perfumestorev1.model.viewmodel.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVM {
    private int id;
    private String typeName;
    private String scentName;
    private String brandName;
    private String name;
    private String description;
    private String genderName;
    private LocalDate createdDate;
    private List<ProductImageVM> productImageVMList;
    private List<ProductSKUVM> productSKUVMList;
}
