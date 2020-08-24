package com.tunabel.perfumestorev1.model.dto;

import com.tunabel.perfumestorev1.data.model.Product;
import lombok.*;

import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuDto {

    @Null
    private int id;

    @Null
    private Integer productId;

    private Product product;

    private String spec;

    private int mainSku;

    private int price;
    private int quantity;
    private int volume;

    private String imageURL;

    @Null
    private Date createdDate;
}
