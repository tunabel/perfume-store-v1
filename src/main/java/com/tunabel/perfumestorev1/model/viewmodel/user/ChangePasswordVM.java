package com.tunabel.perfumestorev1.model.viewmodel.user;

public class ChangePasswordVM {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordVM() {
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String toString() {
        return "ChangePasswordVM(currentPassword=" + this.getCurrentPassword() + ", newPassword=" + this.getNewPassword() + ", confirmPassword=" + this.getConfirmPassword() + ")";
    }
}
