package com.tunabel.perfumestorev1.model.viewmodel.order;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

import java.util.List;

public class OrderDetailVM {

    private HeaderMenuVM headerMenuVM;
    private List<OrderSkuVM> orderSkuVMList;
    private long totalPrice;
    private int totalSku;

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }

    public List<OrderSkuVM> getOrderSkuVMList() {
        return orderSkuVMList;
    }

    public void setOrderSkuVMList(List<OrderSkuVM> orderSkuVMList) {
        this.orderSkuVMList = orderSkuVMList;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalSku() {
        return totalSku;
    }

    public void setTotalSku(int totalSku) {
        this.totalSku = totalSku;
    }
}
