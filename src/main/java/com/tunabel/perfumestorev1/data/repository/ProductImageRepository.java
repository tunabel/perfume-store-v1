package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query("select count(p.id) from ProductImage p")
    long getTotalProductImages();

    @Query("SELECT i FROM ProductImage i " +
            "WHERE i.product = :product")
    List<ProductImage> findAllByProduct(@Param("product") Product product);
}
