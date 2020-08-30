package com.tunabel.perfumestorev1.model.viewmodel.common;

public class HeaderMenuVM {
    private boolean isLoggedIn;
    private int cartQty;

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
}
