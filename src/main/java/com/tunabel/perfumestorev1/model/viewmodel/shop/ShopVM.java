package com.tunabel.perfumestorev1.model.viewmodel.shop;
import com.tunabel.perfumestorev1.model.viewmodel.common.BrandVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSKUVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ScentVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.TypeVM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopVM {
    private List<ProductSKUVM> productSKUVMList;
    private List<BrandVM> brandVMList;
    private List<TypeVM> typeVMList;
    private List<ScentVM> scentVMList;
    private String searchQuery;
}
