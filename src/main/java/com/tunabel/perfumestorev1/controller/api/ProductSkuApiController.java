package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.data.service.ProductService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.ProductSkuDto;
import com.tunabel.perfumestorev1.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sku")
public class ProductSkuApiController {

    @Autowired
    ProductSKUService productSKUService;

    @Autowired
    ProductService productService;


    @GetMapping("/all")
    public ResponseEntity getAllSKUs() {
        List<ProductSku> skuList = productSKUService.getNewArrivalList(12);
        List<ProductSkuDto> skudtoList = new ArrayList<>();
        for (ProductSku sku : skuList) {
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


    @PostMapping("/update/{skuId}")
    public BaseApiResult updateProduct(@PathVariable int skuId,
                                       @RequestBody ProductSkuDto dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductSku sku = productSKUService.findById(skuId);

            if (dto.getMainSku() == 1 && isMainSkuExisted(sku.getProductId(), skuId)) {
                result.setSuccessful(false);
                result.setMessage("This product has already has a Main SKU. Please revise.");
                return result;
            }

            sku.setId(dto.getId());
            sku.setName(dto.getName());
            sku.setSpec(dto.getSpec());
            sku.setPrice(dto.getPrice());
            sku.setQuantity(dto.getQuantity());
            sku.setImageURL(dto.getImageURL());
            sku.setMainSku(dto.getMainSku());
            sku.setCreatedDate(new Date());

            productSKUService.add(sku);
            result.setSuccessful(true);
            result.setMessage("Update product sku successfully");
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping(value = "/create")
    public BaseApiResult createProductSku(@RequestBody ProductSkuDto dto) {
        DataApiResult result = new DataApiResult();

        try {
            ProductSku sku = new ProductSku();

            if (dto.getMainSku() == 1 && isMainSkuExisted(dto.getProductId())) {
                result.setSuccessful(false);
                result.setMessage("This product has already has a Main SKU. Please revise.");
                return result;
            }

            sku.setName(dto.getName());
            sku.setSpec(dto.getSpec());
            sku.setPrice(dto.getPrice());
            sku.setQuantity(dto.getQuantity());
            sku.setImageURL(dto.getImageURL());
            sku.setMainSku(dto.getMainSku());
            sku.setProduct(productService.findOne(dto.getProductId()));
            sku.setCreatedDate(new Date());

            sku = productSKUService.add(sku);
            result.setData(sku.getId());
            result.setMessage("New product SKU created with ID: " + sku.getId());
            result.setSuccessful(true);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    private boolean isMainSkuExisted(int productId, int skuId) {
        ProductSku currentMainSku = productSKUService.getMainSkuByProductId(productId);
        if (currentMainSku.getId() != skuId) {
            return true;
        }
        return false;
    }

    private boolean isMainSkuExisted(int productId) {
        ProductSku currentMainSku = productSKUService.getMainSkuByProductId(productId);
        if (currentMainSku != null) {
            return true;
        }
        return false;
    }

    @DeleteMapping(value = "/delete/{skuId}")
    public BaseApiResult addNewProductImage(@PathVariable int skuId) {
        DataApiResult result = new DataApiResult();

        try {
            boolean isSkuDeleted = productSKUService.deleteOne(skuId);

            if (isSkuDeleted) {
                result.setSuccessful(true);
                result.setMessage("Sku deleted successfully");
            } else {
                result.setSuccessful(false);
                result.setMessage("Sku is already purchased or in cart. Can't be deleted");
            }

        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
