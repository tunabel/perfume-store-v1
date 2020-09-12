package com.tunabel.perfumestorev1.controller.api;


import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.ProductDto;
import com.tunabel.perfumestorev1.model.dto.ProductSkuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sku")
public class ProductSkuApiController {

    @Autowired
    ProductSKUService productSKUService;

    @GetMapping("/all")
    public ResponseEntity getAllSKUs() {
        List<ProductSku> skuList = productSKUService.getNewArrivalList(12);
        List<ProductSkuDto> skudtoList = new ArrayList<>();
        for(ProductSku sku: skuList) {
            ProductSkuDto skudto = new ProductSkuDto();
            skudto.setId(sku.getId());

            skudtoList.add(skudto);
        }

        return new ResponseEntity<>(skudtoList, HttpStatus.OK);
    }

    @GetMapping("/detail/{skuId}")
    public DataApiResult detailSku(@PathVariable int skuId) {
        DataApiResult result = new DataApiResult();

        try {
            ProductSku skuEntity = productSKUService.findById(skuId);
            if (skuEntity == null) {
                result.setSuccessful(false);
                result.setMessage("Can't find this Sku");
            } else {
                ProductSkuDto dto = new ProductSkuDto();
                dto.setId(skuEntity.getId());
                dto.setImageURL(skuEntity.getImageURL());
                dto.setPrice(skuEntity.getPrice());
                dto.setSpec(skuEntity.getSpec());
                dto.setName(skuEntity.getName());
                dto.setMainSku(skuEntity.getMainSku());
                dto.setQuantity(skuEntity.getQuantity());


                dto.setCreatedDate(new Date());


                result.setSuccessful(true);
                result.setData(dto);
                result.setMessage("Success");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
