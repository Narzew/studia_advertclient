package com.company.tools;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.config.Config;
import com.company.exceptions.InvalidResponseCodeException;
import com.company.tools.Hasher;
import org.json.JSONObject;

public class APIHelper {

    /*
    Make call to API
     */

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
            /*
            if (code == 200 || code == 201 || code == 202) {
                return connection.getContent().toString();
            } else throw new InvalidResponseCodeException();
            */
             return connection.getContent().toString();
        } catch (Exception e){
            System.out.println("Error occured: "+e.getMessage());
        }
        return "";
    }

    /*
    Parse login request
     */

    public static String login_user(String email, String password){
        // Parse apiGet for login
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        JSONObject json = new JSONObject(map);
        // Parse apiPost for register
        String response = apiCall(Config.SERVER_URL+"/login/", "POST", json.toString());
        String result = "Response: "+response;
        return result;
    }

    /*
    Parse register request
     */

    public static String register_user(String email, String password, String username, String phoneNumber){
        // Parse apiPost for register
        // Create JSON Object
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        // Generate authkey
        map.put("authkey", Hasher.generate_authkey());
        map.put("username", username);
        map.put("phoneNumber", phoneNumber);
        JSONObject json = new JSONObject(map);

        String response = apiCall(Config.SERVER_URL+"/users/", "POST", json.toString());
        String result = "Response: "+response;
        return result;
    }

}
