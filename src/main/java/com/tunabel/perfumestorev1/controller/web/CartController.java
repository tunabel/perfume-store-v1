package com.tunabel.perfumestorev1.controller.web;

import com.tunabel.perfumestorev1.data.model.Cart;
import com.tunabel.perfumestorev1.data.model.CartSku;
import com.tunabel.perfumestorev1.data.service.CartService;
import com.tunabel.perfumestorev1.model.viewmodel.cart.CartSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.cart.CartVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(path = "/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String cart(Model model,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {

        int cartQty = this.checkCookieAndShowCartQty(response, request, principal);

        CartVM vm = new CartVM();

        int skuQty = 0;
        long totalPrice = 0;
        List<CartSkuVM> cartSkuVMList = new ArrayList<>();

        String guid = getGuid(request);
        try {
            if (guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if (cartEntity != null) {
                    skuQty = cartEntity.getCartSkuList().size();
                    for (CartSku cartSku : cartEntity.getCartSkuList()) {
                        CartSkuVM cartSkuVM = new CartSkuVM();
                        cartSkuVM.setId(cartSku.getId());
                        cartSkuVM.setSkuId(cartSku.getProductSKU().getId());
                        cartSkuVM.setProductName(cartSku.getProductSKU().getName());
                        cartSkuVM.setBrandName(cartSku.getProductSKU().getProduct().getBrand().getName());
                        cartSkuVM.setVolume(cartSku.getProductSKU().getName());
                        cartSkuVM.setSkuImage(cartSku.getProductSKU().getImageURL());
                        cartSkuVM.setQuantity(cartSku.getQuantity());
                        cartSkuVM.setPrice(cartSku.getProductSKU().getPrice());
//                        cartSkuVM.setPrice(String.format(Locale.forLanguageTag("vi"), "%,f.000₫", price));
                        long price = cartSku.getQuantity() * cartSku.getProductSKU().getPrice();
                        totalPrice += price;
                        cartSkuVMList.add(cartSkuVM);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        vm.setSkuQty(skuQty);
        vm.setCartSkuVMList(cartSkuVMList);
//        vm.setTotalPrice(String.format(Locale.forLanguageTag("vi"), "%,f.000₫", totalPrice));
        vm.setTotalPrice(totalPrice);
        vm.setHeaderMenuVM(this.getHeaderMenuVM(cartQty, principal));

        model.addAttribute("vm", vm);

        return "/cart";
    }

    public String getGuid(HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) return c.getValue();
            }
        }
        return null;
    }

}
