package com.tunabel.perfumestorev1.model.viewmodel.user;

public class UserVM {
    private int id;
    private String avatar;
    private String username;
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
    private int gender;

    public UserVM() {
    }

    public int getId() {
        return this.id;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getGender() {
        return this.gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String toString() {
        return "UserVM(id=" + this.getId() + ", avatar=" + this.getAvatar() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", phoneNumber=" + this.getPhoneNumber() + ", gender=" + this.getGender() + ")";
    }
}
