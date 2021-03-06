package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.*;
import com.tunabel.perfumestorev1.data.service.*;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;
import com.tunabel.perfumestorev1.model.viewmodel.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderSkuService orderSkuService;

    @Autowired
    private CartSkuService cartSkuService;

    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {
        CheckoutVM vm = new CheckoutVM();
        OrderVM order = new OrderVM();

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);

        Cookie cookie[] = request.getCookies();

        String guid = null;

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) {
                    guid = c.getValue();
                }
            }
        }

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);
            if (userEntity != null) {
                order.setAddress(userEntity.getAddress());
                order.setCustomerName(userEntity.getName());
                order.setPhone(userEntity.getPhone());
                order.setEmail(userEntity.getEmail());
            }
        }
        long totalPrice = 0;
        Cart cartEntity = cartService.findFirstCartByGuid(guid);
        if (cartEntity != null) {
            for (CartSku cartSku : cartEntity.getCartSkuList()) {
                long price = cartSku.getQuantity() * cartSku.getProductSKU().getPrice();
                totalPrice += price;
            }
        }

        order.setPrice(totalPrice);

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("home");
        vm.setHeaderMenuVM(headerMenuVM);

        model.addAttribute("order", order);
        model.addAttribute("vm", vm);
        return "/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid @ModelAttribute("order") OrderVM orderVM,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {
        Order order = new Order();

        boolean isCookiePresent = false;

        Cookie cookie[] = request.getCookies();

        String guid = null;

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) {
                    isCookiePresent = true;
                    guid = c.getValue();
                }
            }
        }

        if (isCookiePresent == true) {

            long totalPrice = 0;

            if (principal != null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUsername(username);
            }

            order.setGuid(guid);
            order.setAddress(orderVM.getAddress());
            order.setEmail(orderVM.getEmail());
            order.setPhone(orderVM.getPhone());
            order.setName(orderVM.getCustomerName());
            order.setCreatedDate(new Date());
            //0 as new pending order
            order.setStatus(0);


            Cart cartEntity = cartService.findFirstCartByGuid(guid);
            if (cartEntity != null) {
                List<OrderSku> orderSkus = new ArrayList<>();
                for (CartSku cartSku : cartEntity.getCartSkuList()) {
                    OrderSku orderSku = new OrderSku();
                    orderSku.setOrder(order);
                    orderSku.setProductSKU(cartSku.getProductSKU());
                    orderSku.setQuantity(cartSku.getQuantity());

                    int price = cartSku.getQuantity() * cartSku.getProductSKU().getPrice();
                    totalPrice += price;

                    orderSku.setPrice(price);

                    orderSkus.add(orderSku);
                }

                order.setOrderSkuList(orderSkus);
                order.setTotalPrice((int) totalPrice);

                orderService.add(order);
                //delete cart
                cartService.deleteCart(cartEntity.getId());
            }
        }

        return "redirect:/order/history";
    }

    @GetMapping("/history")
    public String orderHistory(Model model, HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        OrderHistoryVM vm = new OrderHistoryVM();

        List<OrderVM> orderVMS = new ArrayList<>();

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);

        Cookie[] cookie = request.getCookies();

        String guid = null;
        boolean flag = false;

        List<Order> orderEntityList = null;

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUserName(null, username);
        } else {
            if (cookie != null) {
                for (Cookie c : cookie) {
                    if (c.getName().equals("guid")) {
                        flag = true;
                        guid = c.getValue();
                    }
                }
                if (flag == true) {
                    orderEntityList = orderService.findOrderByGuidOrUserName(guid, null);
                }
            }
        }

        if (orderEntityList != null) {
            for (Order order : orderEntityList) {
                OrderVM orderVM = new OrderVM();
                orderVM.setId(order.getId());
                orderVM.setCustomerName(order.getName());
                orderVM.setEmail(order.getEmail());
                orderVM.setAddress(order.getAddress());
                orderVM.setPhone(order.getPhone());
                orderVM.setPrice(order.getTotalPrice());
                orderVM.setCreatedDate(order.getCreatedDate());

                orderVMS.add(orderVM);
            }
        }

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("profile");
        vm.setHeaderMenuVM(headerMenuVM);

        vm.setOrderVMS(orderVMS);

        model.addAttribute("vm", vm);

        return "/order-history";
    }


    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model, HttpServletResponse response,
                                 HttpServletRequest request,
                                 @PathVariable("orderId") int orderId,
                                 final Principal principal) {

        OrderDetailVM vm = new OrderDetailVM();

        List<OrderSkuVM> orderSkuVMList = new ArrayList<>();
        long totalPrice = 0;

        Order orderEntity = orderService.findOne(orderId);

        if (orderEntity != null) {
            for (OrderSku orderSku : orderEntity.getOrderSkuList()) {
                OrderSkuVM orderSkuVM = new OrderSkuVM();

                orderSkuVM.setSkuId(orderSku.getProductSKU().getId());
                orderSkuVM.setMainImage(orderSku.getProductSKU().getImageURL());
                orderSkuVM.setQuantity(orderSku.getQuantity());
                orderSkuVM.setSkuName( orderSku.getProductSKU().getProduct().getBrand().getName()+ " - "+  orderSku.getProductSKU().getProduct().getName() + " - "+ orderSku.getProductSKU().getName());

                orderSkuVM.setPrice(orderSku.getPrice());

                totalPrice += orderSku.getPrice();

                orderSkuVMList.add(orderSkuVM);
            }
        }

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);

        HeaderMenuVM headerMenuVM = this.getHeaderMenuVM(cartQty, principal);
        headerMenuVM.setPageName("profile");
        vm.setHeaderMenuVM(headerMenuVM);

        vm.setOrderSkuVMList(orderSkuVMList);
        vm.setTotalPrice(totalPrice);
        vm.setTotalSku(orderSkuVMList.size());

        model.addAttribute("vm", vm);

        return "/order-detail";
    }


}
