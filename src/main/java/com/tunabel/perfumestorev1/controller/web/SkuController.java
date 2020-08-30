package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.Product;
import com.tunabel.perfumestorev1.data.model.ProductImage;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.BrandService;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.data.service.ProductService;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductImageVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.sku.SkuDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class SkuController extends BaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSKUService productSKUService;

    @GetMapping(value = "/product")
    public String detail(Model model,
                         @RequestParam(name = "sku") int skuId,
                         HttpServletResponse response,
                         HttpServletRequest request,
                         final Principal principal) {

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);


        Product product = productService.findBySkuId(skuId);
        ProductSku mainSku = productSKUService.findById(skuId);

        if (mainSku == null) {
            return null;
        }

        SkuDetailVM skuVM = new SkuDetailVM();

        skuVM.setSkuId(skuId);
        skuVM.setSkuName(mainSku.getName());
        skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫", mainSku.getPrice()));

        skuVM.setProductName(product.getName());
        skuVM.setProductDesc(product.getDescription());

        List<ProductSkuVM> childrenSkuList = new ArrayList<>();

        for (ProductSku sku : product.getProductSkuList()) {
            ProductSkuVM childrenSku = new ProductSkuVM();
            childrenSku.setId(sku.getId());
            childrenSku.setImageURL(sku.getImageURL());
            childrenSku.setName(sku.getName());
            childrenSku.setSpec(sku.getSpec());

            childrenSkuList.add(childrenSku);
        }

        skuVM.setChildrenSkuList(childrenSkuList);

        List<ProductImageVM> imageVMList = new ArrayList<>();

        if (!product.getProductImageList().isEmpty()) {
            for (ProductImage image : product.getProductImageList()) {
                ProductImageVM imageVM = new ProductImageVM();
                imageVM.setId(image.getId());
                System.out.println(image.getImageUrl());
                imageVM.setImageURL(image.getImageUrl());

                imageVMList.add(imageVM);
            }
        }

        ProductImageVM mainSkuImageVM = new ProductImageVM();
        mainSkuImageVM.setId(0);
        mainSkuImageVM.setImageURL(mainSku.getImageURL());

        imageVMList.add(0, mainSkuImageVM);
        skuVM.setProductImageVMList(imageVMList);

        skuVM.setTypeName(product.getType().getName());
        skuVM.setTypeId(product.getTypeId());

        skuVM.setScentName(product.getScent().getName());
        skuVM.setScentId(product.getScentId());

        skuVM.setBrandName(product.getBrand().getName());
        skuVM.setBrandId(product.getBrandId());
        skuVM.setBrandDesc(product.getBrand().getDescription());

        skuVM.setGenderId(product.getGender());

        skuVM.setHeaderMenuVM(this.getHeaderMenuVM(cartQty, principal));
        //TODO
        //List Related Skus (top 10 best sellers of same scent


        model.addAttribute("vm", skuVM);
        return "sku";
    }
}
