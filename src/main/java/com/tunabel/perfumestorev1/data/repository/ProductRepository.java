package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;



public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select count(p.id) from Product p")
    long getTotalProducts();

//    @Query("SELECT p FROM dbo_product p " +
//            "WHERE (:brandId IS NULL OR (p.brandId = :brandId))" +
//            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
//     Page<Product> getListProductByBrandOrProductNameContaining(Pageable pageable,
//                                                                          @Param("brandId") Integer brandId,
//                                                                          @Param("productName") String productName);
}
