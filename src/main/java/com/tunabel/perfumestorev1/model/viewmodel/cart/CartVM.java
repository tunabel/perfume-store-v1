package com.tunabel.perfumestorev1.model.viewmodel.cart;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

import java.util.List;

public class CartVM {

    private int skuQty;
    private List<CartSkuVM> cartSkuVMS;
    private String totalPrice;
    private HeaderMenuVM headerMenuVM;

    public int getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(int skuQty) {
        this.skuQty = skuQty;
    }

    public List<CartSkuVM> getCartSkuVMS() {
        return cartSkuVMS;
    }

    public void setCartSkuVMS(List<CartSkuVM> cartSkuVMS) {
        this.cartSkuVMS = cartSkuVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }
}
