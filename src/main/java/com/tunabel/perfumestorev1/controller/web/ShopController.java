package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.model.Scent;
import com.tunabel.perfumestorev1.data.model.Type;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import com.tunabel.perfumestorev1.model.viewmodel.shop.ShopVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
                       @Valid @ModelAttribute("searchProps") ProductSearchVM searchVM,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "12") Integer size,
                       @RequestParam(name = "sortBy", required = false, defaultValue = "best") String sort) {

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

        Page<ProductSku> productSkuPage;
        PageRequest pageRequest = PageRequest.of(page,size);

        if ( searchVM != null) {
            productSkuPage = productSKUService.getPageMainSkuByQuery(searchVM, pageRequest, sort);
        }

        productSkuPage = productSKUService.getPageMainSku(pageRequest, sort);

        List<ProductSkuVM> productSkuVMList = new ArrayList<>();

        for(ProductSku productSKU : productSkuPage.getContent()) {
            ProductSkuVM skuVM = new ProductSkuVM();
            skuVM.setId(productSKU.getId());
            skuVM.setName(productSKU.getProduct().getName());
            skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫",productSKU.getPrice()));
            skuVM.setImageURL(productSKU.getImageURL());
            productSkuVMList.add(skuVM);
        }
//
//        if(productSkuVMList.size() == 0) {
//            vm.setSearchQuery("As the fragrance vanishes, the sensation tingles...");
//        }

        vm.setProductSkuVMList(productSkuVMList);
        vm.setBrandVMList(brandVMList);
        vm.setScentVMList(scentVMList);
        vm.setTypeVMList(typeVMList);

        model.addAttribute("vm",vm);
        model.addAttribute("page",productSkuPage);
        return "shop";
    }


}
