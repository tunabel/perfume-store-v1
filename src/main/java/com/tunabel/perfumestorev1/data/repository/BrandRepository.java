package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Brand;
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
}
