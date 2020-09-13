package com.tunabel.perfumestorev1.model.viewmodel.user;

import java.util.Date;
import java.util.List;

public class UserVM {
    private int id;
    private String avatar;
    private String username;
    private String email;
    private String name;
    private String address;
    private String phone;
    private int gender;
    private int status;
    private long totalSpending;
    private List<String> roles;
    private Date createdDate;

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

    public String getPhone() {
        return this.phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(long totalSpending) {
        this.totalSpending = totalSpending;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String toString() {
        return "UserVM(id=" + this.getId() + ", avatar=" + this.getAvatar() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", phoneNumber=" + this.getPhone() + ", gender=" + this.getGender() + ")";
    }
}
