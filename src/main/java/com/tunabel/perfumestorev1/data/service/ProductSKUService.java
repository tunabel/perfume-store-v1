package com.tunabel.perfumestorev1.data.service;
import com.tunabel.perfumestorev1.data.model.ProductSKU;
import com.tunabel.perfumestorev1.data.repository.ProductSKURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSKUService {

    @Autowired
    private ProductSKURepository productSKURepository;

    public List<ProductSKU> getProductSKUList() {return productSKURepository.getProductSKUList();}

    public List<ProductSKU> getNewArrivalList() {
        List<ProductSKU> skuList = productSKURepository.getNewArrivalList();

        for (ProductSKU productSKU : skuList) {
            System.out.println(productSKU.getName());
        }
        return productSKURepository.getNewArrivalList();
    }

    public List<ProductSKU> getBestSellerList() {
        return productSKURepository.getBestSellerList();
    }

}