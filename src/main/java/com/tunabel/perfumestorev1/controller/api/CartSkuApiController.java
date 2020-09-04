package com.tunabel.perfumestorev1.controller.api;


import com.tunabel.perfumestorev1.data.model.Cart;
import com.tunabel.perfumestorev1.data.model.CartSku;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.data.service.CartService;
import com.tunabel.perfumestorev1.data.service.CartSkuService;
import com.tunabel.perfumestorev1.data.service.ProductSKUService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.dto.CartSkuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cart-sku")
public class CartSkuApiController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartSkuService cartSkuService;

    @Autowired
    private ProductSKUService productSKUService;

    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartSkuDto dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(dto.getGuid() != null && dto.getQty() > 0 && dto.getSkuId() > 0) {
                Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
                ProductSku skuEntity = productSKUService.findById(dto.getSkuId());

                if(cartEntity != null && skuEntity != null)  {
                    CartSku cartSkuEntity = cartSkuService.findFirstCartSkuByCartIdAndSkuId(cartEntity.getId(),skuEntity.getId());
                    if(cartSkuEntity != null) {
                        cartSkuEntity.setQuantity(cartSkuEntity.getQuantity() + dto.getQty());
                        cartSkuService.updateCartSku(cartSkuEntity);
                    } else {
                        CartSku cartSku = new CartSku();
                        cartSku.setQuantity(dto.getQty());
                        cartSku.setCart(cartEntity);
                        cartSku.setProductSKU(skuEntity);
                        cartSkuService.addNewCartSku(cartSku);
                    }
                    result.setMessage("Add to cart successfully!");
                    result.setSuccessful(true);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setMessage("Fail!");
        result.setSuccessful(false);
        return result;
    }

    @PostMapping("/update")
    public BaseApiResult updateCartSku(@RequestBody List<CartSkuDto> dtoList) {

        BaseApiResult result = new BaseApiResult();

        boolean flagResult = true;
        for(CartSkuDto dto: dtoList) {

            try {
                if(dto.getId()>0 && dto.getQty() > 0) {
                    CartSku cartSkuEntity = cartSkuService.findOne(dto.getId());

                    if(cartSkuEntity != null) {
                        cartSkuEntity.setQuantity(dto.getQty());
                        cartSkuService.updateCartSku(cartSkuEntity);
                    }
                }
            } catch (Exception e) {
                flagResult = false;
                e.printStackTrace();
            }
        }

        if(flagResult) {
            result.setMessage("Update Cart Product success !");
            result.setSuccessful(true);
        } else {
            result.setMessage("Fail!");
            result.setSuccessful(false);
        }

        return result;
    }

//    @GetMapping("/delete/{cartSkuId}")
//    public String deleteCartSku(@PathVariable int cartSkuId) {
//        BaseApiResult result = new BaseApiResult();
//
//        try {
//            if(cartSkuService.deleteCartSku(cartSkuId) == true) {
//                result.setMessage("Delete success");
//                result.setSuccessful(true);
//                return "redirect:/cart";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        result.setSuccessful(false);
//        result.setMessage("Delete failure!");
//        return "redirect:/cart";
//    }

}
