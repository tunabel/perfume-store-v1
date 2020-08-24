package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.repository.ProductSKURepository;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSearchVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSKUService {

    @Autowired
    private ProductSKURepository productSKURepository;

    public List<ProductSku> getProductSKUList() {
        return productSKURepository.getProductSKUList();
    }

    public List<ProductSku> getNewArrivalList(int limit) {
        List<ProductSku> skuList = productSKURepository.getNewArrivals(limit);

        return productSKURepository.getNewArrivals(limit);
    }

    public List<ProductSku> getBestSellerList(int limit) {
        return productSKURepository.getBestSellers(limit);
    }

    public Page<ProductSku> getPageMainSkuByQuery(ProductSearchVM searchVM, PageRequest pageRequest, String sort) {
        return null;
    }

    public Page<ProductSku> getPageMainSku(PageRequest pageRequest, String sort) {
        Page<ProductSku> productSkuPage;

        switch (sort) {
            case "best":
                productSkuPage = productSKURepository.getPageMainSkuBySales(pageRequest);
                break;
            case "new":
                productSkuPage = productSKURepository.getPageMainSku(pageRequest, "s.createdDate DESC");
                break;
            case "priceAsc":
                productSkuPage = productSKURepository.getPageMainSku(pageRequest, "s.price ASC");
                break;
            case "priceDesc":
                productSkuPage = productSKURepository.getPageMainSku(pageRequest, "s.price DESC");
                break;
            default:
                productSkuPage = productSKURepository.getPageMainSkuBySales(pageRequest);
                break;
        }

        return productSkuPage;

    }

    public Optional<ProductSku> findById(int skuId) {
        return productSKURepository.findById(skuId);
    }
}