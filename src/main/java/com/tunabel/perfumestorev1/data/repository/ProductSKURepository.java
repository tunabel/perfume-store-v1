package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSku;
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

    @Query("SELECT s FROM ProductSku s " +
            "WHERE s.mainSku = 1 " +
            "ORDER BY :sort")
    Page<ProductSku> getPageMainSku(Pageable pageRequest, @Param("sort") String sort);


    @Query("SELECT s FROM ProductSku s "+
            "WHERE s.mainSku = 1")
    Page<ProductSku> getPageMainSkuBySales(Pageable pageRequest);
}
