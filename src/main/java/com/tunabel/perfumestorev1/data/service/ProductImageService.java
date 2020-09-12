package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductImage;
import com.tunabel.perfumestorev1.data.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Transactional
    public void addNewListProductImages(List<ProductImage> productImages) {
        try {
            productImageRepository.save(productImages);
        } catch (Exception e) {
        }
    }

    public void addNewProductImage(ProductImage productImage) {
        try {
            productImageRepository.save(productImage);
        } catch (Exception e) {
        }
    }

    public ProductImage findOne(int productImageId) {
        return productImageRepository.findOne(productImageId);
    }

    public boolean updateProductImage(ProductImage productImage) {
        try {
            productImageRepository.save(productImage);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteProductImage(int productImageId) {
        try {
            productImageRepository.delete(productImageId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public List<ProductImage> getListAllProductImages() {
        try {
            return productImageRepository.findAll();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public long getTotalProductImages() {
        return productImageRepository.getTotalProductImages();
    }

    public List<ProductImage> findAllByProduct(Product product) {
        return productImageRepository.findAllByProduct(product);
    }
}
