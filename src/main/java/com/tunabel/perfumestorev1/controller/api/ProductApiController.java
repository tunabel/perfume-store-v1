package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.repository.ProductRepository;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ScentService scentService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSKUService productSKUService;


    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ProductDto dto) {
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setBrand(brandService.findOne(dto.getBrandId()));
            product.setScent(scentService.findOne(dto.getScentId()));
            product.setType(typeService.findOne(dto.getTypeId()));
            product.setGender(dto.getGender());
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);

            result.setData(product.getId());
            result.setMessage("Save product successfully: " + product.getId());
            result.setSuccessful(true);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDto dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = productService.findOne(productId);
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setBrand(brandService.findOne(dto.getBrandId()));
            product.setScent(scentService.findOne(dto.getScentId()));
            product.setType(typeService.findOne(dto.getTypeId()));
            product.setGender(dto.getGender());
            product.setCreatedDate(new Date());

            productService.addNewProduct(product);
            result.setSuccessful(true);
            result.setMessage("Update product successfully");
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{productId}")
    public DataApiResult detailProduct(@PathVariable int productId) {
        DataApiResult result = new DataApiResult();

        try {
            Product productEntity = productService.findOne(productId);
            if (productEntity == null) {
                result.setSuccessful(false);
                result.setMessage("Can't find this product");
            } else {
                ProductDto dto = new ProductDto();
                dto.setId(productEntity.getId());
                if (productEntity.getBrand() != null) {
                    dto.setBrandId(productEntity.getBrandId());
                }

                if (productEntity.getScent() != null) {
                    dto.setScentId(productEntity.getScentId());
                }

                if (productEntity.getType() != null) {
                    dto.setTypeId(productEntity.getTypeId());
                }

                dto.setGender(productEntity.getGender());

                ProductSku mainSku = productSKUService.getMainSkuByProductId(dto.getId());

                if (mainSku.getImageURL() != null) {
                    dto.setMainImageURL(mainSku.getImageURL());
                }

                dto.setName(productEntity.getName());
                dto.setDescription(productEntity.getDescription());
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

//
//    @GetMapping("/query/method-name")
//    public DataApiResult testQueryNameMethod(@RequestParam(name = "name", defaultValue = "", required = false) String name) {
//        DataApiResult result = new DataApiResult();
//
//        List<Product> productList = productRepository.findByNameContainingIgnoreCase(name);
//        List<ProductDTO> productDTOS = new ArrayList<>();
//
//        for (Product productEntity : productList) {
//            ProductDTO dto = new ProductDTO();
//            dto.setId(productEntity.getId());
//            if (productEntity.getCategory() != null) {
//                dto.setCategoryId(productEntity.getCategory().getId());
//            }
//            dto.setMainImage(productEntity.getMainImage());
//            dto.setName(productEntity.getName());
//            dto.setPrice(productEntity.getPrice());
//            dto.setShortDesc(productEntity.getShortDesc());
//            dto.setCreatedDate(productEntity.getCreatedDate());
//
//            productDTOS.add(dto);
//        }
//
//        result.setMessage("success");
//        result.setData(productDTOS);
//        result.setSuccess(true);
//        return result;
//    }
//
//    @GetMapping("/query/jpql")
//    public DataApiResult testQueryJPQL(@RequestParam(name = "name", defaultValue = "", required = false) String name) {
//        DataApiResult result = new DataApiResult();
//        List<ProductDTO> productDTOS = productRepository.getListByNameContaining(name);
//
//        result.setMessage("success");
//        result.setData(productDTOS);
//        result.setSuccess(true);
//        return result;
//    }
//
//    @GetMapping("/query/native")
//    public DataApiResult testQueryNative(@RequestParam(name = "name", defaultValue = "", required = false) String name) {
//        DataApiResult result = new DataApiResult();
//        List<Object[]> data = productRepository.getListByNameContainingNative(name);
//
//        result.setMessage("success");
//        result.setData(data);
//        result.setSuccess(true);
//        return result;
//    }
}
