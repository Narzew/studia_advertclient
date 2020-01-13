package com.company.application;

import com.company.data.UserData;
import com.company.tools.APIHelper;
import com.company.tools.InputHelper;

import java.util.Scanner;

public class Menu {

    /*
    Main menu
    */

    public static void mainMenu() {
        System.out.println("AdvertClient v 0.1");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int completed_action = 0;
        if (UserData.logged()) {
            userMenu();
        } else {
            guestMenu();
        }
    }

    /*
    Menu for not logged users
     */

    public static void guestMenu() {
        System.out.println("\n1 - login\n2 - register\n3 - all adverts\n0 - exit");
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            choice = scanner.nextInt();
            switch (choice) {
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
                    APIHelper.get_all_adverts();
                    break;
            }
            mainMenu();
        } while (choice == 0);
    }

    /*
    Menu for logged users
     */

    public static void userMenu(){
        String simplified;
        if(APIHelper.simplified == false) {
            simplified = "enable";
        } else {
            simplified = "disable";
        }
        System.out.println(String.format("\n1 - add advert\n2 - %s simplified mode\n3 - get user adverts\n4 - get all adverts\n5 - modify advert\n6 - remove adverts\n7 - get exchange rate\n8 - logout\n0 - exit",simplified));
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(-1);
                    break;
                case 1:
                    InputHelper.parseAdvert("add");
                    break;
                case 2:
                    InputHelper.swapSimplifiedMode();
                    break;
                case 3:
                    APIHelper.get_user_adverts(UserData.getId());
                    break;
                case 4:
                    APIHelper.get_all_adverts();
                    break;
                case 5:
                    InputHelper.modifyAdvert();
                    break;
                case 6:
                    InputHelper.removeAdvert();
                    break;
                case 7:
                    InputHelper.getExchangeRate();
                    break;
                case 8:
                    InputHelper.logout();
            }
            mainMenu();
        } while (choice == 0);
    }

}
