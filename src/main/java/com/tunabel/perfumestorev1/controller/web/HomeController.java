package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.BrandService;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.data.service.ProductService;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.home.HomePageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class HomeController extends BaseController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSKUService productSKUService;

    @GetMapping(value = "")
    public String home(Model model) {

        HomePageVM vm = new HomePageVM();

        /**
         * set list new Arrivals
         */

        List<ProductSku> newArrivalList = productSKUService.getNewArrivalList(12);
        List<ProductSkuVM> newArrivalVMList = new ArrayList<>();

        for(ProductSku productSKU : newArrivalList) {
            ProductSkuVM skuVM = new ProductSkuVM();
            skuVM.setId(productSKU.getId());
            skuVM.setBrand(productSKU.getProduct().getBrand().getName());
            skuVM.setName(productSKU.getProduct().getName() +" - " + productSKU.getName());
            skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫",productSKU.getPrice()));
            skuVM.setImageURL(productSKU.getImageURL());
            newArrivalVMList.add(skuVM);
        }

        vm.setNewArrivalList(newArrivalVMList);
        vm.setBestSellerList(newArrivalVMList);
        //TODO BestSeller List

        model.addAttribute("vm",vm);
        return "home";
    }


}
