package com.company.tools;

import com.company.config.UserData;

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
        System.out.println("\n1 - add advert\n2 - search for advert\n3 - get user adverts\n4 - get all adverts\n5 - modify advert\n6 - remove adverts\n7 - logout\n0 - exit");
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(-1);
                    break;
                case 1:
                    InputHelper.addAdvert();
                    break;
                case 2:
                    InputHelper.searchForAdvert();
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
                    InputHelper.logout();
            }
            mainMenu();
        } while (choice == 0);
    }

}
