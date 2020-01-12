package com.company.config;

public class UserData {

    /*
    Class containing global variables related with user.
     */

    public static Integer id = 0;
    public static String authkey = "";

    public static Boolean logged(){
        if(id.equals(0)){
            return false;
        } else {
            return true;
        }
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        UserData.id = id;
    }

    public static String getAuthkey() {
        return authkey;
    }

    public static void setAuthkey(String authkey) {
        UserData.authkey = authkey;
    }
}
