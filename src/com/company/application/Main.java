package com.company.application;

import com.company.config.UserData;
import com.company.tools.APIHelper;
import com.company.tools.Hasher;
import com.company.tools.InputHelper;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Test register - register a user
        try {
            APIHelper.register_user("narzew@gmail.com", Hasher.hash_password("nikodem"), "Nikodem Solarz", "+48 530 996 646");
        } catch(Exception e){
            System.out.println("Exception occured: "+e.getMessage());
        }
        //System.exit(-1);
        InputHelper.mainMenu();
    }
}
