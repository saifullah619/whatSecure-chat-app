package com.murtaza.infosecuritychatapp;

public class CurrentUser {
    public static String user;

    CurrentUser() {}

    CurrentUser(String user) {
        this.user = user;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        CurrentUser.user = user;
    }
}
