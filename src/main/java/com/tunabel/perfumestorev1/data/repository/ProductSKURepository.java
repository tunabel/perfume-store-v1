package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductSKURepository extends JpaRepository<ProductSKU,Integer> {

    @Query("SELECT s.id, s.imageURL, s.price, s.name, s.product FROM ProductSKU s")
    List<ProductSKU> getNewArrivalList();



//    @Query("SELECT s.id, p.name, s.price, s.imageURL FROM ProductSKU s "+
//            "JOIN s.product p "+
//            "JOIN dbo_order_sku o ON s.id = o.productSKU.id "+
//            "WHERE s.status = 1 "+
//            "ORDER BY s.createdDate DESC")
    @Query("SELECT s FROM ProductSKU s")
    List<ProductSKU> getBestSellerList();

//    @Query("SELECT p FROM dbo_product p " +
//            "WHERE (:brandId IS NULL OR (p.brandId = :brandId))" +
//            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
//     Page<Product> getListProductByBrandOrProductNameContaining(Pageable pageable,
//                                                                          @Param("brandId") Integer brandId,
//                                                                          @Param("productName") String productName);

    @Query("SELECT s FROM ProductSKU s WHERE s.status = 1")
    List<ProductSKU> getProductSKUList();
}
