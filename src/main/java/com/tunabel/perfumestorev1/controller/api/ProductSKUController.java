package com.tunabel.perfumestorev1.controller.api;


import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.model.dto.ProductSkuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sku")
public class ProductSKUController {

    @Autowired
    ProductSKUService productSKUService;

    @GetMapping("/all")
    public ResponseEntity getAllSKUs() {
        List<ProductSku> skuList = productSKUService.getNewArrivalList(12);
        List<ProductSkuDto> skudtoList = new ArrayList<>();
        for(ProductSku sku: skuList) {
            ProductSkuDto skudto = ProductSkuDto.builder().id(sku.getId()).build();
            skudtoList.add(skudto);
        }

        return new ResponseEntity<>(skudtoList, HttpStatus.OK);
    }

}
