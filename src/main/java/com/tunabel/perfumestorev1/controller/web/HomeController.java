package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.ProductSKU;
import com.tunabel.perfumestorev1.data.service.BrandService;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.data.service.ProductService;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSKUVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductVM;
import com.tunabel.perfumestorev1.model.viewmodel.home.HomePageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController extends BaseController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSKUService productSKUService;

    @GetMapping(value = "")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName) {

        HomePageVM vm = new HomePageVM();

        /**
         * set list new Arrivals
         */

        List<ProductSKU> newArrivalList = productSKUService.getNewArrivalList();
        List<ProductSKUVM> newArrivalVMList = new ArrayList<>();

        for(ProductSKU productSKU : newArrivalList) {
            ProductSKUVM skuVM = new ProductSKUVM();
            skuVM.setId(productSKU.getId());
            skuVM.setName(productSKU.getProduct().getName() +" " + productSKU.getName());
            skuVM.setPrice(productSKU.getPrice());
            skuVM.setImageURL(productSKU.getImageURL());
            newArrivalVMList.add(skuVM);
        }

        vm.setNewArrivalList(newArrivalVMList);
        vm.setBestSellerList(newArrivalVMList);

        model.addAttribute("vm",vm);
        return "home";
    }


}
