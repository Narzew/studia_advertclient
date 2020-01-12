package com.company.application;

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
        System.out.println("AdvertClient v 0.1\n1 - login\n2 - register\n3 - public adverts\n0 - exit");
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        int completed_action=0;
        do {
            choice = scanner.nextInt();
            switch(choice){
                case 0:
                    System.exit(-1);
                    break;
                case 1:
                    InputHelper.parse_login();
                    break;
                case 2:
                    InputHelper.parse_register();
                    break;
                case 3:
                    InputHelper.parse_adverts();
                    break;
            }
        } while (choice==0);
    }
}
