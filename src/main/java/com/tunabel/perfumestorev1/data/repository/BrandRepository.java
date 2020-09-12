package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    @Query("select count(b.id) from Brand b")
    long getTotalCountBrands();

    @Query("SELECT b FROM Brand b")
    List<Brand> getBrandList();

}
