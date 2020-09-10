package com.tunabel.perfumestorev1.model.viewmodel.common;

public class HeaderMenuAdminVM {
    private String username;
    private String avatarURL;

    public HeaderMenuAdminVM(String username, String avatarURL) {
        this.username = username;
        this.avatarURL = avatarURL;
    }

    public HeaderMenuAdminVM() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
