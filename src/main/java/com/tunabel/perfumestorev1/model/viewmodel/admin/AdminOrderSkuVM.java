package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;

import java.util.Date;
import java.util.List;

public class AdminOrderSkuVM {

    private List<ProductSkuVM> productSkuVMList;
    private int orderId;
    private int orderStatus;
    private String username;
    private String address;
    private Date createdDate;

    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<ProductSkuVM> getProductSkuVMList() {
        return productSkuVMList;
    }

    public void setProductSkuVMList(List<ProductSkuVM> productSkuVMList) {
        this.productSkuVMList = productSkuVMList;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
