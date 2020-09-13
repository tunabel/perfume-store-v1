package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.*;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.admin.*;
import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import com.tunabel.perfumestorev1.model.viewmodel.order.OrderSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.order.OrderVM;
import com.tunabel.perfumestorev1.model.viewmodel.user.UserVM;
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
import java.util.stream.Collectors;

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
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderSkuService orderSkuService;
    @Autowired
    private RoleService roleService;

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
        Page<Product> productPage;

        if (searchVM.getName() != null && !searchVM.getName().isEmpty()) {
            productPage = productService.getPageWithSearch(pageRequest, searchVM.getName().trim());
            vm.setSearch("Find with key: " + searchVM.getName());
        } else productPage = productService.getPageWithSearch(pageRequest, null);

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

        Page<Brand> brandPage = null;

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

    @GetMapping("/order")
    public String getOrders(Model model,
                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                            @RequestParam(name = "user", required = false) String username
    ) {
        AdminOrderVM vm = new AdminOrderVM();

        Pageable pageable = new PageRequest(page, size);
        Page<Order> orderPage;
        if (username != null && !username.isEmpty()) {
            orderPage = orderService.getPageByUsername(pageable, username);
        } else {
            orderPage = orderService.getPage(pageable);
        }

        List<OrderVM> orderVMList = new ArrayList<>();

        for (Order order : orderPage.getContent()) {
            OrderVM orderVM = new OrderVM();

            orderVM.setId(order.getId());
            orderVM.setUsername(order.getUsername());
            orderVM.setCustomerName(order.getName());
            orderVM.setAddress(order.getAddress());
            orderVM.setPhone(order.getPhone());
            orderVM.setEmail(order.getEmail());
            orderVM.setPrice(order.getTotalPrice());
            orderVM.setStatus(order.getStatus());
            orderVM.setCreatedDate(order.getCreatedDate());

            orderVMList.add(orderVM);
        }

        vm.setHeaderMenuAdminVM(this.getHeaderMenuAdminVM());
        vm.setOrderVMList(orderVMList);

        model.addAttribute("vm", vm);
        model.addAttribute("page", orderPage);

        return "/admin/order";
    }


    @GetMapping("/order-sku/{orderId}")
    public String getOrderSkuDetails(Model model, @PathVariable int orderId) {
        AdminOrderSkuVM vm = new AdminOrderSkuVM();

        Order order = orderService.findOne(orderId);

        if (order != null) {
            vm.setUsername(order.getUsername());
            vm.setAddress(
                    "Receiver: " + order.getName()
                            + "\nAddress: " + order.getAddress()
                            + "\nPhone: " + order.getPhone()
                            + "\nEmail: " + order.getEmail()
            );
            vm.setOrderStatus(order.getStatus());
            vm.setOrderId(orderId);
            vm.setCreatedDate(order.getCreatedDate());
            vm.setTotalPrice(order.getTotalPrice());

            List<OrderSkuVM> orderSkuVMList = new ArrayList<>();

            for (OrderSku orderSku : order.getOrderSkuList()) {
                OrderSkuVM orderSkuVM = new OrderSkuVM();

                orderSkuVM.setSkuId(orderSku.getProductSKU().getId());
                orderSkuVM.setMainImage(orderSku.getProductSKU().getImageURL());
                orderSkuVM.setQuantity(orderSku.getQuantity());
                orderSkuVM.setSkuName(orderSku.getProductSKU().getProduct().getBrand().getName() + " - " + orderSku.getProductSKU().getProduct().getName() + " - " + orderSku.getProductSKU().getName());
                orderSkuVM.setPrice(orderSku.getPrice());
                orderSkuVM.setUnitPrice(orderSku.getProductSKU().getPrice());
                orderSkuVMList.add(orderSkuVM);
            }
            vm.setOrderSkuVMList(orderSkuVMList);
        }

        model.addAttribute("vm", vm);

        return "/admin/order-sku";
    }

    @GetMapping("/user")
    public String getUsers(Model model,
                           @Valid @ModelAttribute("name") UserVM searchVM,
                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "5") Integer size
    ) {
        AdminUserVM vm = new AdminUserVM();

        Pageable pageable = new PageRequest(page, size);

        Page<User> userPage;

        if (searchVM.getName() != null && !searchVM.getName().isEmpty()) {
            userPage = userService.getUserListByNameContaining(pageable, searchVM.getName().trim());
            vm.setSearch("Find with key: " + searchVM.getName());
        } else userPage = userService.getUserListByNameContaining(pageable, null);

        List<UserVM> userVMList = new ArrayList<>();

        for (User user : userPage.getContent()) {
            UserVM userVM = new UserVM();

            userVM.setId(user.getId());
            userVM.setUsername(user.getUsername());
            userVM.setAvatar(user.getAvatarURL());
            userVM.setEmail(user.getEmail());

            userVM.setName(user.getName());
            userVM.setAddress(user.getAddress());
            userVM.setPhone(user.getPhone());
            userVM.setGender(user.getGender());
            userVM.setStatus(user.getStatus());

            List<Order> userOrderList = orderService.findAllByUsername(user.getUsername());

            if (userOrderList.size() > 0) {
                long totalSpending = 0L;

                for(Order order: userOrderList) {
                    totalSpending += order.getTotalPrice();
                }
                userVM.setTotalSpending(totalSpending);
//
//                userVM.setTotalSpending(
//                        userOrderList.stream()
//                                .map(item -> item.getTotalPrice())
//                                .reduce(0L, (subtotal, item) ->
//                                        subtotal + item
//                                ));
            }

            List<Role> userRoles = roleService.findAllByUserId(user.getId());

            userVM.setRoles(userRoles.stream().map(role -> role.getName()).collect(Collectors.toList()));
            userVM.setCreatedDate(user.getCreatedDate());

            userVMList.add(userVM);
        }

        vm.setHeaderMenuAdminVM(this.getHeaderMenuAdminVM());
        vm.setUserVMList(userVMList);

        model.addAttribute("vm", vm);
        model.addAttribute("page", userPage);

        return "/admin/user";
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

}
