package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    @Query("select count(b.id) from Brand b")
    long getTotalCountBrands();

    @Query("SELECT b FROM Brand b")
    List<Brand> getBrandList();

    @Query("SELECT b FROM Brand b " +
            "WHERE (:brand IS NULL OR UPPER(b.name) LIKE CONCAT('%',UPPER(:brand),'%'))")
    Page<Brand> getBrandListByNameContaining(Pageable pageable, @Param("brand") String brandName);

    @Query("SELECT new com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM(b.name, SUM(os.price)) FROM Brand b " +
            "JOIN b.listProducts p " +
            "JOIN p.productSkuList ps " +
            "JOIN ps.orderSkuList os " +
            "JOIN os.order o " +
            "WHERE YEAR(o.createdDate) = :year " +
            "GROUP BY p.id " +
            "ORDER BY SUM(os.price) DESC")
    List<ChartDataVM> getBestSellersBrandOfYear(@Param("year") int year);
}
