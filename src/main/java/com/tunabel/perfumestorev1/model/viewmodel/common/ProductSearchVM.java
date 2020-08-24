package com.tunabel.perfumestorev1.model.viewmodel.common;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchVM {
    private int[] type;
    private int[] scent;
    private int[] brand;
    private int[] gender;
    private String name;
}
