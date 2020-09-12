package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.repository.ProductSKURepository;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSearchVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSKUService {

    @Autowired
    private ProductSKURepository productSKURepository;

    public List<ProductSku> getProductSKUList() {
        return productSKURepository.getProductSKUList();
    }
    public Page<ProductSku>  getProductSkuPageWithSearch(String search, Pageable pageable) {
        return productSKURepository.getProductSkuPageWithSearch(pageable, search);
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

    public ProductSku getMainSkuByProductId(int id) {
        return productSKURepository.getMainSkuByProductId(id);
    };

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

    public ProductSku findById(int skuId) {
        return productSKURepository.findOne(skuId);
    }

    public List<ProductSku> findAllByProductId(int productId) {
        return productSKURepository.findAllByProductId(productId);
    }

    public ProductSku add(ProductSku sku) {
        return productSKURepository.save(sku);
    }
}