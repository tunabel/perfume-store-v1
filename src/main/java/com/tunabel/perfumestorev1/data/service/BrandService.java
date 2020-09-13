package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public long getTotalBrands() {
        return brandRepository.getTotalCountBrands();
    }

    public List<Brand> getBrandList() {
        try {
            return brandRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Brand findOne(int brandId) {
        return brandRepository.findOne(brandId);
    }

    public Page<Brand> getBrandListByNameContaining(Pageable pageable, String brandName) {
        return brandRepository.getBrandListByNameContaining(pageable, brandName);
    }

    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }
}
