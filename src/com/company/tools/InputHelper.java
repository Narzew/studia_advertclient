package com.company.tools;

import com.company.application.Menu;
import com.company.config.Categories;
import com.company.data.TempData;
import com.company.data.UserData;

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

    public static void logout(){
        UserData.setId(0);
        UserData.setAuthkey("");
        Menu.mainMenu();
    }

     /*
    Get register data from user
     */

    public static void parseAdvert(String mode){
        if(mode=="modify" && TempData.lastAdvertId == 0){
            System.out.println("Cannot perform modify. Missing advert ID.");
            return;
        }
        if(mode=="delete"){
            if(TempData.lastAdvertId == 0) {
                System.out.println("Cannot perform delete. Missing advert ID.");
                return;
            } else {
                APIHelper.delete_advert(TempData.lastAdvertId);
                return;
            }
        }
        Scanner scanner = new Scanner(System.in);
        String title;
        String content;
        String region;
        String category;
        String price;
        String result = "";
        int choice = 0;
        do {
            System.out.println("Enter title:");
            title = scanner.nextLine();
        } while (title.isBlank());

        do {
            System.out.println("Enter description:");
            content = scanner.nextLine();
        } while (content.isBlank());

        do {
            System.out.println("Enter region (for example Poland, Germany, USA, Iran, Russia):");
            region = scanner.nextLine();
        } while (region.isBlank());

        do {
            System.out.println("Enter category. Available categories:\n"+ Categories.getCategoryStr());
            category = scanner.nextLine();
        } while (category.isBlank() || !(Categories.isCategoryValid(category)));

        do {
            System.out.println("Enter price in format price currency for example 600PLN:");
            price = scanner.nextLine();
        } while (price.isBlank() || !(Money.validatePrice(price)));

        switch(mode) {
            case "add":
                result = APIHelper.add_advert(title, content, region, category, price);
                break;
            case "modify":
                result = APIHelper.modify_advert(TempData.lastAdvertId, title, content, region, category, price);
                break;
        }
        System.out.println(result);
    }

    public static void modifyAdvert(){
        Scanner scanner = new Scanner(System.in);
        Integer advertId;
        System.out.println("Your adverts:");
        APIHelper.get_user_adverts(UserData.getId());
        do {
            System.out.println("Enter ID of advert to modify:");
            System.out.println("Warning: putting bad ID will lead in refused request");
            advertId = scanner.nextInt();
        } while (advertId <= 0);
        TempData.lastAdvertId = advertId;
        InputHelper.parseAdvert("modify");
    }

    public static void removeAdvert(){
        Scanner scanner = new Scanner(System.in);
        Integer advertId;
        System.out.println("Your adverts:");
        APIHelper.get_user_adverts(UserData.getId());
        do {
            System.out.println("Enter ID of advert to delete:");
            System.out.println("Warning: This operation cannot be undone.");
            System.out.println("Warning: putting bad ID will lead in refused request");
            advertId = scanner.nextInt();
        } while (advertId <= 0);
        TempData.lastAdvertId = advertId;
        InputHelper.parseAdvert("delete");
    }

    public static void swapSimplifiedMode(){
        APIHelper.simplified = !APIHelper.simplified;
    }

}
