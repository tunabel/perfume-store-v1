package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;
import com.tunabel.perfumestorev1.model.viewmodel.order.OrderVM;

import java.util.List;

public class AdminOrderVM {

    private List<OrderVM> orderVMList;
    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<OrderVM> getOrderVMList() {
        return orderVMList;
    }

    public void setOrderVMList(List<OrderVM> orderVMList) {
        this.orderVMList = orderVMList;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }
}
