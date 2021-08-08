package com.pocket.pos.model;

import com.pocket.pos.entity.Role;

public class UserModel extends EntityCommonsModel {

    private String name;
    private String phone;
    private Long userId;
    private String password;
    private String userName;
    private Role role;

    UserModel() {
    }

    public UserModel(Long userId, String name, String phone, String password, String userName, Role role) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

