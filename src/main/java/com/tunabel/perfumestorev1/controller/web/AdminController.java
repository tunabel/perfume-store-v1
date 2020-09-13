package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.*;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.admin.*;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ScentService scentService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSKUService productSKUService;
    @Autowired
    private ProductImageService productImageService;


    @GetMapping("")
    public String admin(Model model) {

        HomeAdminVM vm = new HomeAdminVM();
        vm.setHeaderMenuAdminVM(this.getHeaderMenuAdminVM());
        List<ChartDataVM> chartDataVMS = new ArrayList<>();

        List<Brand> brandList = brandService.getBrandList();

        for (Brand brand : brandList) {
            chartDataVMS.add(new ChartDataVM(brand.getName(), (long) brand.getListProducts().size()));
        }

//        vm.setChartDataVMS(chartDataVMS);

        model.addAttribute("vm", vm);
        return "/admin/home";
    }

    @GetMapping("/product")
    public String product(Model model,
                          @Valid @ModelAttribute("productname") ProductSearchVM searchVM,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "2") Integer size,
                          @RequestParam(name = "brandId", required = false) Integer brandId

    ) {
        AdminProductVM vm = new AdminProductVM();

        /**
         * set list brandVM
         */
        List<Brand> brandList = brandService.getBrandList();
        List<BrandVM> brandVMList = new ArrayList<>();

        for (Brand brand : brandList) {
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

        for (Scent scent : scentList) {
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

        for (Type type : typeList) {
            TypeVM typeVM = new TypeVM();
            typeVM.setId(type.getId());
            typeVM.setName(type.getName());
            typeVMList.add(typeVM);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<Product> productPage = productService.getPageWithSearch(pageRequest, searchVM.getName());


        List<ProductVM> productVMList = new ArrayList<>();

        for (Product product : productPage.getContent()) {
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setBrand(product.getBrand().getName());
            productVM.setBrandId(product.getBrandId());
            productVM.setScent(product.getScent().getName());
            productVM.setType(product.getType().getName());
            productVM.setDesc(product.getDescription());
            ProductSku sku = productSKUService.getMainSkuByProductId(product.getId());
            if (sku != null) {
                productVM.setImageURL(sku.getImageURL());
            } else {
                productVM.setImageURL("");
            }
            productVM.setCreatedDate(product.getCreatedDate());
            productVMList.add(productVM);
        }

        if (productVMList.size() == 0) {
            vm.setSearch("As the fragrance vanishes, the sensation tingles...");
        }

        vm.setProductVMList(productVMList);
        vm.setBrandVMList(brandVMList);
        vm.setScentVMList(scentVMList);
        vm.setTypeVMList(typeVMList);

        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);

        return "/admin/product";
    }

    @GetMapping("/product-sku/{productId}")
    public String productSku(Model model, @PathVariable int productId) {
        AdminProductSkuVM vm = new AdminProductSkuVM();

        Product product = productService.findOne(productId);

        if (product != null) {
            vm.setProductNameAndBrand(product.getBrand().getName() + " - " + product.getName());
            vm.setProductId(productId);
        }

        List<ProductSkuVM> productSkuVMList = new ArrayList<>();

        List<ProductSku> productSkuList = productSKUService.findAllByProductId(productId);


        if (productSkuList.size() > 0) {

            for (ProductSku productSku : productSkuList) {
                ProductSkuVM skuVM = new ProductSkuVM();
                skuVM.setId(productSku.getId());
                skuVM.setName(productSku.getName());

                skuVM.setSpec(productSku.getSpec());
                skuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫", productSku.getPrice()));
                skuVM.setQuantity(productSku.getQuantity());
                skuVM.setImageURL(productSku.getImageURL());
                skuVM.setMainSku(productSku.getMainSku());
                skuVM.setCreatedDate(productSku.getCreatedDate());
                productSkuVMList.add(skuVM);
            }
        }

        vm.setProductSkuVMList(productSkuVMList);
        model.addAttribute("vm", vm);

        return "/admin/product-sku";
    }

    @GetMapping("/product-image/{productId}")
    public String getProductImages(Model model, @PathVariable int productId) {
        AdminProductImageVM vm = new AdminProductImageVM();

        Product product = productService.findOne(productId);

        if (product != null) {
            vm.setProductNameAndBrand(product.getBrand().getName() + " - " + product.getName());
            vm.setProductId(productId);
        }

        List<ProductImageVM> productImageVMList = new ArrayList<>();

        List<ProductImage> productImageList = productImageService.findAllByProduct(product);


        if (productImageList.size() > 0) {

            for (ProductImage productImage : productImageList) {
                ProductImageVM imageVM = new ProductImageVM();
                imageVM.setId(productImage.getId());
                imageVM.setImageURL(productImage.getImageUrl());

                imageVM.setCreatedDate(productImage.getCreatedDate());
                productImageVMList.add(imageVM);
            }
        }

        vm.setProductImageVMList(productImageVMList);
        model.addAttribute("vm", vm);

        return "/admin/product-image";
    }

    @GetMapping("/brand")
    public String getBrands(Model model,
                          @Valid @ModelAttribute("search") BrandVM brandSearch,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "5") Integer size
    ) {
        AdminBrandVM vm = new AdminBrandVM();

        Pageable pageable = new PageRequest(page, size);

        Page<Brand> brandPage =  null;

        if (brandSearch.getName() != null && !brandSearch.getName().isEmpty()) {
            brandPage = brandService.getBrandListByNameContaining(pageable, brandSearch.getName().trim());
            vm.setSearch("Find with key: " + brandSearch.getName());
        } else brandPage = brandService.getBrandListByNameContaining(pageable, null);

        List<BrandVM> brandVMList = new ArrayList<>();

        for (Brand brand : brandPage.getContent()) {
            BrandVM brandVM = new BrandVM();

            brandVM.setId(brand.getId());
            brandVM.setName(brand.getName());
            brandVM.setDescription(brand.getDescription());
            brandVM.setProductCount(brand.getListProducts().size());
//            brandVM.setCreatedDate(brand.getCreatedDate());

            brandVMList.add(brandVM);
        }

        vm.setHeaderMenuAdminVM(this.getHeaderMenuAdminVM());
        vm.setBrandVMList(brandVMList);
        if (brandVMList.size() == 0) {
            vm.setSearch("No brand found");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", brandPage);

        return "/admin/brand";
    }
//
//    @GetMapping("/chart")
//    public String chart(Model model) {
//
//        ChartVM vm = new ChartVM();
//
//        vm.setHeaderMenuAdminVM(this.getHeaderMenuAdminVM());
//
//        List<ChartDataVM> chartDataVMS = new ArrayList<>();
//
//        List<Brand> brandList = brandService.getBrandList();
//
//        for(Brand brand : brandList) {
//            chartDataVMS.add(new ChartDataVM(brand.getName(), (long) brand.getListProducts().size()));
//        }
//
//        vm.setChartDataVMS(chartDataVMS);
//
//        model.addAttribute("vm", vm);
//
//        return "/admin/chart";
//    }
//
//    @GetMapping("/product-image/{productId}")
//    public String product(Model model, @PathVariable int productId) {
//        AdminProductImageVM vm = new AdminProductImageVM();
//
//        Product productEntity = productService.findOne(productId);
//
//
//        List<ProductImageVM> productImageVMS = new ArrayList<>();
//
//        for (ProductImage productImage : productEntity.getProductImageList()) {
//            ProductImageVM productImageVM = new ProductImageVM();
//            productImageVM.setId(productImage.getId());
//            productImageVM.setLink(productImage.getLink());
//            productImageVM.setCreatedDate(productImage.getCreatedDate());
//            productImageVMS.add(productImageVM);
//        }
//
//        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
//        vm.setProductImageVMS(productImageVMS);
//        vm.setProductId(productId);
//
//
//        model.addAttribute("vm", vm);
//
//        return "/admin/product-image";
//    }

}
