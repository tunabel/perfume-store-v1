package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.service.BrandService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/brand")
public class BrandApiController {

    @Autowired
    private BrandService brandService;

    @PostMapping(value = "/create")
    public BaseApiResult createBrand(@RequestBody BrandDto dto) {
        DataApiResult result = new DataApiResult();

        try {
            Brand brand = new Brand();
            brand.setName(dto.getName());
            brand.setDescription(dto.getDescription());
            brand.setCreatedDate(new Date());
            brand = brandService.add(brand);
            result.setData(brand.getId());
            result.setMessage("Brand "+brand.getName()+" created successfully with ID:  " + brand.getId());
            result.setSuccessful(true);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{brandId}")
    public BaseApiResult updateBrand(@PathVariable int brandId,
                                        @RequestBody BrandDto dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Brand brand = brandService.findOne(brandId);
            if (brand == null) {
                result.setSuccessful(false);
                result.setMessage("No Brand found");
            } else {
                brand.setName(dto.getName());
                brand.setDescription(dto.getDescription());
                brand.setCreatedDate(new Date());
                brandService.add(brand);
                result.setSuccessful(true);
                result.setMessage("Brand updated successfully");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{brandId}")
    public DataApiResult detailProduct(@PathVariable int brandId) {
        DataApiResult result = new DataApiResult();

        try {
            Brand brandEntity = brandService.findOne(brandId);
            if (brandEntity == null) {
                result.setSuccessful(false);
                result.setMessage("No Brand found");
            } else {
                BrandDto dto = new BrandDto();
                dto.setId(brandEntity.getId());
                dto.setName(brandEntity.getName());
                dto.setDescription(brandEntity.getDescription());
//                dto.setCreatedDate(brandEntity.getCreatedDate());
                result.setSuccessful(true);
                result.setData(dto);
                result.setMessage("Success");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
