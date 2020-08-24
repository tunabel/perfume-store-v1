package com.tunabel.perfumestorev1.model.viewmodel.home;

import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVM {
    private List<ProductSkuVM> newArrivalList;
    private List<ProductSkuVM> bestSellerList;
    private String searchQuery;
}
