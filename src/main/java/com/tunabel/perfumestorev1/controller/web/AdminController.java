package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.*;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.admin.AdminProductVM;
import com.tunabel.perfumestorev1.model.viewmodel.admin.ChartVM;
import com.tunabel.perfumestorev1.model.viewmodel.admin.HomeAdminVM;
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
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
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
            ProductSku sku = productSKUService.getSkuByProductId(product.getId());
            if (sku != null) {
                productVM.setImageURL(sku.getImageURL());
            }
//            productVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,d.000₫", product.getPrice()));
//            productVM.setImageURL(product.getImageURL());
            productVM.setCreatedDate(product.getCreatedDate());
            productVMList.add(productVM);
        }
//
        if (productVMList.size() == 0) {
            vm.setSearch("As the fragrance vanishes, the sensation tingles...");
        }
//
        vm.setProductVMList(productVMList);
        vm.setBrandVMList(brandVMList);
        vm.setScentVMList(scentVMList);
        vm.setTypeVMList(typeVMList);
//
//
        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);

        return "/admin/product";
    }
//
//
//    @GetMapping("/category")
//    public String category(Model model,
//                          @Valid @ModelAttribute("categoryname") CategoryVM categoryName,
//                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
//    ) {
//        AdminCategoryVM vm = new AdminCategoryVM();
//
//
//        Pageable pageable = new PageRequest(page, size);
//
//        Page<Category> categoryPage =  null;
//
//
//        if (categoryName.getName() != null && !categoryName.getName().isEmpty()) {
//            categoryPage = brandService.getListCategoryByCategoryNameContaining(pageable, categoryName.getName().trim());
//            vm.setKeyWord("Find with key: " + categoryName.getName());
//        } else categoryPage = brandService.getListCategoryByCategoryNameContaining(pageable, null);
//
//
//        List<CategoryVM> categoryVMList = new ArrayList<>();
//
//        for (Category category : categoryPage.getContent()) {
//            CategoryVM categoryVM = new CategoryVM();
//
//            categoryVM.setId(category.getId());
//            categoryVM.setName(category.getName());
//            categoryVM.setShortDesc(category.getShortDesc());
//            categoryVM.setCreatedDate(category.getCreatedDate());
//
//            categoryVMList.add(categoryVM);
//        }
//
//        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
//        vm.setCategoryVMList(categoryVMList);
//        if (categoryVMList.size() == 0) {
//            vm.setKeyWord("Not found any category");
//        }
//
//
//        model.addAttribute("vm", vm);
//        model.addAttribute("page", categoryPage);
//
//        return "/admin/category";
//    }
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
