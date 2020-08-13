package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.model.ProductSKU;
import com.tunabel.perfumestorev1.data.model.Scent;
import com.tunabel.perfumestorev1.data.model.Type;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import com.tunabel.perfumestorev1.model.viewmodel.shop.ShopVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ShopController extends BaseController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ScentService scentService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSKUService productSKUService;

    @GetMapping(value = "/shop")
    public String shop(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       @RequestParam(name = "brandId", required = false) Integer brandId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "12") Integer size,
                       @RequestParam(name = "sortByPrice", required = false) String sort) {

        ShopVM vm = new ShopVM();

        /**
         * set list brandVM
         */
        List<Brand> brandList = brandService.getBrandList();
        List<BrandVM> brandVMList = new ArrayList<>();

        for(Brand brand: brandList) {
            BrandVM brandVM = new BrandVM();
            brandVM.setId(brand.getId());
            brandVM.setName(brand.getName());
            brandVMList.add(brandVM);
        }

        /**
         * set list scentVM
         */
        List<Scent> scentList = scentService.getScentList();
        List<ScentVM> scentVMList = new ArrayList<>();

        for(Scent scent: scentList) {
            ScentVM scentVM = new ScentVM();
            scentVM.setId(scent.getId());
            scentVM.setName(scent.getName());
            scentVMList.add(scentVM);
        }

        /**
         * set list typeVM
         */
        List<Type> typeList = typeService.getTypeList();
        List<TypeVM> typeVMList = new ArrayList<>();

        for(Type type: typeList) {
            TypeVM typeVM = new TypeVM();
            typeVM.setId(type.getId());
            typeVM.setName(type.getName());
            typeVMList.add(typeVM);
        }


        /**
         * set list product SKUs
         */

        List<ProductSKU> productSKUList = productSKUService.getProductSKUList();
        List<ProductSKUVM> productSKUVMList = new ArrayList<>();

        for(ProductSKU productSKU : productSKUList) {
            ProductSKUVM skuVM = new ProductSKUVM();
            skuVM.setId(productSKU.getId());
            skuVM.setName(productSKU.getProduct().getName());
            skuVM.setPrice(productSKU.getPrice());
            skuVM.setImageURL(productSKU.getImageURL());
            productSKUVMList.add(skuVM);
        }

        if(productSKUVMList.size() == 0) {
            vm.setSearchQuery("As the fragrance vanishes, the sensation tingles...");
        }

        vm.setProductSKUVMList(productSKUVMList);
        vm.setBrandVMList(brandVMList);
        vm.setScentVMList(scentVMList);
        vm.setTypeVMList(typeVMList);

        model.addAttribute("vm",vm);
        return "shop";
    }


}
