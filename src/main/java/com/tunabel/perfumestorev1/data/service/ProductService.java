package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void addNewListProducts(List<Product> listProducts) {
        productRepository.save(listProducts);
    }

    public Product findOne(int productId) {
        return productRepository.findOne(productId);
    }

    public boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int productId) {
        try {
            productRepository.delete(productId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Product> getListAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public long getTotalProducts(){
        return productRepository.getTotalProducts();
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getListProductByBrandOrProductNameContaining(Pageable pageable, Integer brandId, String productName){
//        return productRepository.getListProductByBrandOrProductNameContaining(pageable,brandId,productName);
        return null;
    }

    public Product findBySkuId(int skuId) {
        return productRepository.findBySkuId(skuId);
    }

    public Page<Product> getPageWithSearch(Pageable pageable, String search) {
        return productRepository.findAllWithSearch(pageable, search);
    }


    public Product findOneByNameAndBrandId(String productName, int brandId) {
        return productRepository.findOneByNameAndBrandId(productName.trim(), brandId);
    }
}