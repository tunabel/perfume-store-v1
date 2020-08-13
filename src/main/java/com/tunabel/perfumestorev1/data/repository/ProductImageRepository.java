package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

        @Query("select count(p.id) from ProductImage p")
        long getTotalProductImages();

}
