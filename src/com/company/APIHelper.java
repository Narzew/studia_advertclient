package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

public class APIHelper {

    /* OLD api call
    public static String apiCall(String url, String method, String data){
        HttpResponse<String> response;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(method, HttpRequest.BodyPublishers.ofString(data))
                .build();
        if(request.)
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.toString();
        } catch (IOException e){
            System.out.println("Error: IOException: " + e.getMessage());
        } catch (InterruptedException e){
            System.out.println("Error: InterruptedException: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Unknown error");
        }
        return "";
    }*/

    public static String apiCall(String addr, String method, String data){
        try {
            URL url = new URL(addr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            if(method.equals("POST") || method.equals("PUT")) {
                connection.setDoOutput(true);
                // Pass JSON over POST
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }
            connection.connect();
            int code = connection.getResponseCode();
            System.out.println("Response code of the object is " + code);
            if (code == 200) {
                return connection.getContent().toString();
            } else throw new InvalidResponseCodeException();
        } catch (Exception e){
            System.out.println("Error occured");
        }
        return "";
    }

    /*
    TODO: Parse login request
     */

    public static String login_user(String email, String password){
        // Parse apiGet for login
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        JSONObject json = new JSONObject(map);
        // Parse apiPost for register
        String response = apiCall("localhost/users/login/", "POST", json.toString());
        String result="CALL OK: "+response;
        return result;
    }

    /*
    TODO: Parse register request
     */

    public static String register_user(String email, String password, String username){
        // Parse apiPost for register
        // Create JSON Object
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("username", username);
        JSONObject json = new JSONObject(map);

        String response = apiCall("localhost/users/", "POST", json.toString());
        String result = "CALL OK: "+response;
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
            System.out.println("Enter login:");
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

        String result = register_user(email, password, username);
    }

    public static void parse_debug(){

    }

}
