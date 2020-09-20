package com.tunabel.perfumestorev1.model.viewmodel.aboutus;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

public class AboutUsVM {
    private HeaderMenuVM headerMenuVM;
    private String searchQuery;

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
