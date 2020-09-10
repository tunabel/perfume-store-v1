package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductSKURepository extends JpaRepository<ProductSku, Integer>, ProductSKURepositoryCustom {

    @Query("SELECT s FROM ProductSku s")
    List<ProductSku> getProductSKUList();

    @Query("SELECT s FROM ProductSku s WHERE (:search IS NULL OR UPPER(s.name) LIKE CONCAT('%',UPPER(:search),'%'))")
    Page<ProductSku> getProductSkuPageWithSearch(Pageable pageRequest, @Param("search") String search);

    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.mainSku = 1 " +
            "ORDER BY :sort")
    Page<ProductSku> getPageMainSku(Pageable pageRequest, @Param("sort") String sort);


    @Query("SELECT s FROM ProductSku s "+
            "WHERE s.mainSku = 1")
    Page<ProductSku> getPageMainSkuBySales(Pageable pageRequest);

    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.productId = :id AND s.mainSku = 1")
    ProductSku getSkuByProductId(@Param("id") int id);


//    @Query(nativeQuery = true, value = "SELECT ps.sku_id, b.name, p.name, ps.name FROM dbo_product_sku ps " +
//            "JOIN dbo_product p " +
//            "ON ps.product_id = p.product_id " +
//            "JOIN dbo_product_brand b " +
//            "ON b.brand_id = p.brand_id " +
//            "WHERE ps.sku_id IN ( " +
//            "SELECT ps.sku_id FROM dbo_product_sku ps " +
//            "JOIN dbo_order_sku os " +
//            "ON os.sku_id = ps.sku_id " +
//            "GROUP BY ps.sku_id, os.order_id " +
//            "HAVING os.order_id IN ( " +
//            "SELECT o.order_id FROM dbo_order o " +
//            "WHERE MONTH(o.created_date) = :month AND YEAR(o.created_date) = :year) " +
//            "ORDER BY SUM(os.quantity) DESC )")
//    List<ChartDataVM> getListFiveHotProduct(Pageable pageable, int month, int year);
}
