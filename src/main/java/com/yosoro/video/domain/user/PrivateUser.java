package com.yosoro.video.domain.user;

public class PrivateUser {
    protected int id;
    protected String password;
    protected String mail;
    protected int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "PrivateUser{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", userType=" + userType +
                '}';
    }
}
