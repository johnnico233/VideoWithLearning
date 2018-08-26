package com.yosoro.video.domain.user;

import java.util.Date;

public class User extends PrivateUser{
    private String userName;
    private Date lastLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", lastLogin=" + lastLogin +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
