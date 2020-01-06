package com.company.tools;

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

    public static void parse_adverts(){

    }

}
