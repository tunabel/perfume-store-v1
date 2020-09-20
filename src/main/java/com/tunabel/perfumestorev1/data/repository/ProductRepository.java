package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> , ProductRepositoryCustom {

    @Query("select count(p.id) from Product p")
    long getTotalProducts();

//    @Query("SELECT p FROM Product p " +
//            "JOIN ProductSku s " +
//            "WHERE s.id = :skuId")
//    Product findBySkuId(@Param("skuId") int skuId);

    @Query(value = "SELECT p.* FROM dbo_product p " +
            "WHERE p.product_id = (" +
                "SELECT s.product_id " +
                "FROM dbo_product_sku s " +
                "WHERE s.sku_id = :skuId" +
            ")", nativeQuery = true)
    Product findBySkuId(@Param("skuId") int skuId);

    @Query("SELECT p FROM Product p WHERE (:search IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:search),'%'))")
    Page<Product> findAllWithSearch(Pageable pageable,  @Param("search") String search);

    @Query("SELECT p FROM Product p WHERE UPPER(p.name) = UPPER(:productName) AND p.brandId = :brandId")
    Product findOneByNameAndBrandId(@Param("productName") String productName, @Param("brandId") int brandId);

    @Query("SELECT new com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM(p.name, SUM(os.price)) FROM Product p " +
            "JOIN p.productSkuList ps " +
            "JOIN ps.orderSkuList os " +
            "JOIN os.order o " +
            "WHERE MONTH(o.createdDate) = :month " +
            "AND YEAR(o.createdDate) = :year " +
            "GROUP BY p.id " +
            "ORDER BY SUM(os.price) DESC")
    List<ChartDataVM> getBestSellersOfMonth(@Param("month") int monthValue, @Param("year") int year);

//    @Query("SELECT p FROM dbo_product p " +
//            "WHERE (:brandId IS NULL OR (p.brandId = :brandId))" +
//            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
//     Page<Product> getListProductByBrandOrProductNameContaining(Pageable pageable,
//                                                                          @Param("brandId") Integer brandId,
//                                                                          @Param("productName") String productName);
}
