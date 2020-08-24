package com.tunabel.perfumestorev1.model.viewmodel.sku;

import com.tunabel.perfumestorev1.model.viewmodel.common.ProductImageVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkuDetailVM {
    private String typeName;
    private int typeId;

    private String scentName;
    private int scentId;

    private String brandName;
    private int brandId;
    private String brandDesc;

    private int genderId;

    private String productName;
    private String productDesc;

    private int skuId;
    private String skuName;
    private String price;
    private int quantity;

    private List<ProductImageVM> productImageVMList;
    private List<ProductSkuVM> childrenSkuList;
    private List<ProductSkuVM> relatedSkuList;
}
