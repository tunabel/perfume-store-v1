package com.tunabel.perfumestorev1.model.viewmodel.cart;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

import java.util.List;

public class CartVM {

    private int skuQty;
    private List<CartSkuVM> cartSkuVMList;
    private long totalPrice;
    private HeaderMenuVM headerMenuVM;

    public int getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(int skuQty) {
        this.skuQty = skuQty;
    }

    public List<CartSkuVM> getCartSkuVMList() {
        return cartSkuVMList;
    }

    public void setCartSkuVMList(List<CartSkuVM> cartSkuVMList) {
        this.cartSkuVMList = cartSkuVMList;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }
}
