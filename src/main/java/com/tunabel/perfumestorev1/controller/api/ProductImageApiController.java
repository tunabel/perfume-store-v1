package com.tunabel.perfumestorev1.controller.api;


import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductImage;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.ProductImageService;
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
@RequestMapping("/api/product-image")
public class ProductImageApiController {

    @Autowired
    ProductSKUService productSKUService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/update/{imageId}")
    public BaseApiResult updateProduct(@PathVariable int imageId,
                                       @RequestParam("imageURL") String imageURL) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductImage image = productImageService.findOne(imageId);

            if (image != null) {
                image.setImageUrl(imageURL);
                productImageService.addNewProductImage(image);

                result.setSuccessful(true);
                result.setMessage("Update image successfully");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping(value = "/create")
    public BaseApiResult addNewProductImage(@RequestParam("imageURL") String imageURL, @RequestParam("productId") int productId) {
        DataApiResult result = new DataApiResult();

        try {

            ProductImage image = new ProductImage();
            Product product = productService.findOne(productId);

            image.setImageUrl(imageURL);
            image.setProduct(product);
            image.setCreatedDate(new Date());

            productImageService.addNewProductImage(image);

            result.setSuccessful(true);
            result.setMessage("New image added successfully");

        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @DeleteMapping(value = "/delete/{imageId}")
    public BaseApiResult addNewProductImage(@PathVariable int imageId) {
        DataApiResult result = new DataApiResult();

        try {

            ProductImage image = productImageService.findOne(imageId);

            productImageService.deleteProductImage(imageId);

            fileStorageService.deleteOne("/../" + image.getImageUrl());

            result.setSuccessful(true);
            result.setMessage("Image deleted successfully");

        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
