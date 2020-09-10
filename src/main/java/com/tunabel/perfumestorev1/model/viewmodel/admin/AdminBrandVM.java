package com.tunabel.perfumestorev1.model.viewmodel.admin;


import com.tunabel.perfumestorev1.model.viewmodel.common.BrandVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;

import java.util.List;

public class AdminBrandVM {

    private List<BrandVM> categoryVMList;
    private String keyWord;
    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<BrandVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<BrandVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }
}
