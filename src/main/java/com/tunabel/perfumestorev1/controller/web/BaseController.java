package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.Cart;
import com.tunabel.perfumestorev1.data.model.CartSku;
import com.tunabel.perfumestorev1.data.model.User;
import com.tunabel.perfumestorev1.data.service.CartService;
import com.tunabel.perfumestorev1.data.service.CartSkuService;
import com.tunabel.perfumestorev1.data.service.UserService;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.UUID;

public class BaseController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartSkuService cartSkuService;

    @Autowired
    private UserService userService;

    public int checkCookieAndShowCartQty(HttpServletResponse response,
                                         HttpServletRequest request,
                                         final Principal principal) {
        //i need number of productSkus in cart
        int cartQty = 0;
        Cookie cookie[] = request.getCookies();
        //if user is authenticated (principal is not null)
        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cart cartEntity = cartService.findByUserName(username);

            if (cartEntity != null) { //if authenticated user has cart
                Cookie cookie1 = new Cookie("guid", cartEntity.getGuid());
                cookie1.setPath("/");
                cookie1.setMaxAge(60 * 60 * 24);
                cartQty = cartSkuService.countSkuQty(cartEntity.getId());
                response.addCookie(cookie1);
            } else { //if user doesn't have cart, create new cart for him
                UUID uuid = UUID.randomUUID();
                String guid = uuid.toString();
                Cart cart = new Cart();
                cart.setGuid(guid);
                cart.setUsername(username);
                cartService.addNewCart(cart);

                Cookie cookie2 = new Cookie("guid", guid);
                cookie2.setPath("/");
                cookie2.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie2);
            }
            //if user is not logged in / guest
        } else {
            boolean flag2 = true;

            String guid = null;
            //if guest has visited and has cookies
            if (cookie != null) {
                for (Cookie c : cookie) {
                    //search for guid. if there is cookies > find cart in DB
                    if (c.getName().equals("guid")) {
                        flag2 = false;
                        guid = c.getValue();
                        Cart guestCart = cartService.findFirstCartByGuid(guid);
                        cartQty = cartSkuService.countSkuQty(guestCart.getId());
                    }
                }
            }

            //if guest doesnt have cookies > create new cart
            if (flag2 == true) {
                UUID uuid = UUID.randomUUID();
                String guid2 = uuid.toString();
                Cart cart2 = new Cart();
                cart2.setGuid(guid2);
                cartService.addNewCart(cart2);

                Cookie cookie3 = new Cookie("guid", guid2);
                cookie3.setPath("/");
                cookie3.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie3);

            } else {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if (cartEntity == null) {
                    Cart cart3 = new Cart();
                    cart3.setGuid(guid);
                    cartService.addNewCart(cart3);
                }
            }

        }
        return cartQty;
    }

    public HeaderMenuVM getHeaderMenuVM(int cartQty,
                                        final Principal principal) {
        HeaderMenuVM headerMenuVM = new HeaderMenuVM();
        headerMenuVM.setCartQty(cartQty);
        headerMenuVM.setLoggedIn(principal != null);

        return headerMenuVM;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {

        HeaderMenuAdminVM vm = new HeaderMenuAdminVM();

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        if(userEntity!=null) {
            vm.setUsername(username);
            if(userEntity.getAvatarURL() != null) {
                vm.setAvatarURL(userEntity.getAvatarURL());
            } else vm.setAvatarURL("/images/blank_avatar.png");
        }

        return vm;

    }

}
