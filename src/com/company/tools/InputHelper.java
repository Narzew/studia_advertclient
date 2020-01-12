package com.company.tools;

import com.company.config.UserData;

import java.util.Scanner;

public class InputHelper {

     /*
    Get login data from user
     */

    public static void parse_login() {
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;
        int choice = 0;
        do {
            System.out.println("Enter e-mail:");
            email = scanner.nextLine();
        } while (email.isBlank());
        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        } while (password.isBlank());

        /* Hash password */
        try {
            password = Hasher.hash_password(password);
        } catch (Exception e) {
            System.out.println("Something went wrong.\n" + e.getMessage());
        }

        String result = APIHelper.login_user(email, password);
        System.out.println(result);

        // TODO: Split ID and authkey and save it to a variable.

    }

    /*
    Get register data from user
     */

    public static void parse_register(){
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;
        String username;
        String phoneNumber;
        int choice = 0;

        do {
            System.out.println("Enter e-mail:");
            email = scanner.nextLine();
        } while (email.isBlank() || !Validator.validateEmail(email));

        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        } while (password.isBlank());

        do {
            System.out.println("Enter username: (First name + Last name)");
            username = scanner.nextLine();
        } while (username.isBlank());

        do {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.nextLine();
        } while (phoneNumber.isBlank() || !Validator.validatePhoneNumber(phoneNumber));

        /* Hash password */
        try {
            password = Hasher.hash_password(password);
        } catch (Exception e) {
            System.out.println("Something went wrong.\n" + e.getMessage());
        }

        String result = APIHelper.register_user(email, password, username, phoneNumber);
        System.out.println(result);
    }

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
        System.out.println("\n1 - login\n2 - register\n3 - public adverts\n0 - exit");
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
                    APIHelper.get_public_adverts();
                    break;
            }
            mainMenu();
        } while (choice == 0);
    }

    /*
    Menu for logged users
     */

    public static void userMenu(){
        System.out.println("\n1 - add advert\n2 - search for advert\n3 - get user adverts\n4 - modify advert\n5 - remove advert\n6 - public adverts\n7 - logout\n0 - exit");
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
                    InputHelper.modifyAdvert();
                    break;
                case 5:
                    InputHelper.removeAdvert();
                    break;
                case 6:
                    APIHelper.get_public_adverts();
                    break;
                case 7:
                    logout();
            }
            mainMenu();
        } while (choice == 0);
    }

    public static void logout(){
        UserData.setId(0);
        UserData.setAuthkey("");
        mainMenu();
    }

    public static void addAdvert(){

    }

    public static void modifyAdvert(){

    }

    public static void removeAdvert(){

    }

    public static void searchForAdvert(){

    }

}
