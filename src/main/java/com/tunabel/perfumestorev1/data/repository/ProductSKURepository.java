package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
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


    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.mainSku = 1")
    Page<ProductSku> getPageMainSkuBySales(Pageable pageRequest);

    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.productId = :id AND s.mainSku = 1")
    ProductSku getMainSkuByProductId(@Param("id") int id);

    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.productId = :productId")
    List<ProductSku> findAllByProductId(@Param("productId") int productId);


    @Query(value = "SELECT * FROM dbo_product_sku s " +
            "WHERE s.sku_id = :skuId AND s.sku_id NOT IN " +
            "(" +
            "SELECT cs.sku_id FROM dbo_cart_sku cs " +
            "GROUP BY cs.sku_id " +
            "UNION " +
            "SELECT os.sku_id FROM dbo_order_sku os " +
            "GROUP BY os.sku_id" +
            ")", nativeQuery = true)
    ProductSku findByIdNotInOrderOrCart(@Param("skuId") int skuId);

    @Query(value = "SELECT * FROM dbo_product_sku s " +
            "WHERE s.sku_id IN " +
            "( " +
            "SELECT os.sku_id FROM dbo_order_sku os " +
            "WHERE os.order_id = :orderId" +
            ")", nativeQuery = true)
    List<ProductSku> findAllByOrderId(@Param("orderId") int orderId);

    @Query(value = "SELECT p.*, SUM(op.quantity)AS SALES FROM dbo_product_sku p " +
            "JOIN dbo_order_sku op " +
            "ON op.sku_id = p.sku_id " +
            "GROUP BY p.sku_id " +
            "ORDER BY SALES DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<ProductSku> getBestSellers(@Param("limit") int limit);
}
