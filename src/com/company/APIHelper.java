package com.company;

import java.util.Scanner;

public class APIHelper {

    /*
    TODO: Parse login request
     */

    public static String login_user(String login, String password){
        // Parse apiGet for login
        // TODO: Method body
        String result = "";
        return result;
    }

    /*
    TODO: Parse register request
     */

    public static String register_user(String login, String password, String username, String email){
        // Parse apiPost for register
        // TODO: Method body
        String result = "";
        return result;
    }

    /*
    Get login data from user
     */

    public static void parse_login() {
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        int choice = 0;
        do {
            System.out.println("Enter login");
            login = scanner.nextLine();
        } while (!login.isBlank());
        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        } while (!password.isBlank());

        /* Hash password */
        try {
            password = Engine.hash_password(password);
        } catch (Exception e) {
            System.out.println("Something went wrong.\n" + e.getMessage());
        }

        String authkey = login_user(login, password);
    }

    /*
    Get register data from user
     */

    public static void parse_register(){
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        String email;
        String username;
        int choice = 0;
        do {
            System.out.println("Enter login");
            login = scanner.nextLine();
        } while (!login.isBlank());

        do {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        } while (!password.isBlank());

        do {
            System.out.println("Enter username:");
            username = scanner.nextLine();
        } while (!username.isBlank());

        do {
            System.out.println("Enter e-mail:");
            email = scanner.nextLine();
        } while (!email.isBlank());

        /* Hash password */
        try {
            password = Engine.hash_password(password);
        } catch (Exception e) {
            System.out.println("Something went wrong.\n" + e.getMessage());
        }

        String result = register_user(login, password, username, email);
    }

    public static void parse_debug(){

    }

    /*
    Get value from external api using get method
    TODO: Method body
     */

    public static void apiGet(){

    }

    /*
    Post value to external API
    TODO: Method body
     */

    public static void apiPost(){

    }

    /*
    CRUD: Update
    TODO: Method body
     */

    public static void apiUpdate(){

    }

    /*
    CRUD: Delete
    TODO: Method body
     */

    public static void apiDelete(){

    }

}
