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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
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
    public String home(Model model, HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {

        this.checkCookie(response, request, principal);

        HomePageVM vm = new HomePageVM();

        List<ProductSku> newArrivalList = productSKUService.getNewArrivalList(12);
        //TODO BestSeller List
        List<ProductSku> bestSellerList = productSKUService.getNewArrivalList(5);

        List<ProductSkuVM> newArrivalVMList = new ArrayList<>();
        for (ProductSku productSKU : newArrivalList) {
            ProductSkuVM skuVM = new ProductSkuVM();
            skuVM.setId(productSKU.getId());
            skuVM.setBrand(productSKU.getProduct().getBrand().getName());
            skuVM.setName(productSKU.getProduct().getName());
            skuVM.setSpec(productSKU.getName());
            skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫", productSKU.getPrice()));
            skuVM.setImageURL(productSKU.getImageURL());
            newArrivalVMList.add(skuVM);
        }
        List<ProductSkuVM> bestSellerVMList = new ArrayList<>();
        for (ProductSku productSKU : bestSellerList) {
            ProductSkuVM skuVM = new ProductSkuVM();
            skuVM.setId(productSKU.getId());
            skuVM.setBrand(productSKU.getProduct().getBrand().getName());
            skuVM.setName(productSKU.getProduct().getName());
            skuVM.setSpec(productSKU.getName());
            skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫", productSKU.getPrice()));
            skuVM.setImageURL(productSKU.getImageURL());
            bestSellerVMList.add(skuVM);
        }

        vm.setNewArrivalList(newArrivalVMList);
        vm.setBestSellerList(bestSellerVMList);


        model.addAttribute("vm", vm);
        return "home";
    }


}
