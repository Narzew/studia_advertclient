package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("AdvertClient v 0.1\n1 - login\n2 - register\n3 - debug\n0 - exit");
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
                    APIHelper.parse_login();
                    break;
                case 2:
                    APIHelper.parse_register();
                    break;
                case 3:
                    APIHelper.parse_debug();
                    break;
            }
        } while (choice==0);
    }
}
