package com.tunabel.perfumestorev1.model.viewmodel.home;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;

import java.util.List;

public class HomePageVM {
    private List<ProductSkuVM> newArrivalList;
    private List<ProductSkuVM> bestSellerList;
    private HeaderMenuVM headerMenuVM;
    private String searchQuery;

    public HomePageVM() {
    }

    public HomePageVM(List<ProductSkuVM> newArrivalList, List<ProductSkuVM> bestSellerList, HeaderMenuVM headerMenuVM, String searchQuery) {
        this.newArrivalList = newArrivalList;
        this.bestSellerList = bestSellerList;
        this.headerMenuVM = headerMenuVM;
        this.searchQuery = searchQuery;
    }

    public List<ProductSkuVM> getNewArrivalList() {
        return this.newArrivalList;
    }

    public List<ProductSkuVM> getBestSellerList() {
        return this.bestSellerList;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public void setNewArrivalList(List<ProductSkuVM> newArrivalList) {
        this.newArrivalList = newArrivalList;
    }

    public void setBestSellerList(List<ProductSkuVM> bestSellerList) {
        this.bestSellerList = bestSellerList;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }
}
