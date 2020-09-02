package com.tunabel.perfumestorev1.model.viewmodel.order;


import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

import java.util.List;

public class OrderHistoryVM {

    private HeaderMenuVM headerMenuVM;
    private List<OrderVM> orderVMS;

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }

    public List<OrderVM> getOrderVMS() {
        return orderVMS;
    }

    public void setOrderVMS(List<OrderVM> orderVMS) {
        this.orderVMS = orderVMS;
    }
}
