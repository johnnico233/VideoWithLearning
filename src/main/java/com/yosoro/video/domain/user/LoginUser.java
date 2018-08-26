package com.yosoro.video.domain.user;

public class LoginUser extends PrivateUser{
    private boolean remember;

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "remember=" + remember +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
