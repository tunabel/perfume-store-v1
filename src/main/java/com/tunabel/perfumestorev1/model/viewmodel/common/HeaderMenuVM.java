package com.tunabel.perfumestorev1.model.viewmodel.common;

public class HeaderMenuVM {
    private boolean isLoggedIn;
    private int cartQty;
    private String pageName;

    public HeaderMenuVM(boolean isLoggedIn, int cartQty) {
        this.isLoggedIn = isLoggedIn;
        this.cartQty = cartQty;
    }

    public HeaderMenuVM() {
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getCartQty() {
        return cartQty;
    }

    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
