package com.tunabel.perfumestorev1.controller.api;


import com.tunabel.perfumestorev1.data.model.ProductSKU;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.model.dto.ProductSKUDTO;
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
        List<ProductSKU> skuList = productSKUService.getNewArrivalList();
        List<ProductSKUDTO> skudtoList = new ArrayList<>();
        for(ProductSKU sku: skuList) {
            ProductSKUDTO skudto = ProductSKUDTO.builder().id(sku.getId()).build();
            skudtoList.add(skudto);
        }

        return new ResponseEntity<>(skudtoList, HttpStatus.OK);
    }

}
