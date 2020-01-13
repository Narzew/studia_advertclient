package com.company.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.company.config.Config;
import com.company.data.UserData;
import com.company.data.Advert;
import com.google.gson.*;
import org.json.JSONObject;

public class APIHelper {

    public static boolean simplified = true;

    /*
    Make call to API
     */

    public static String apiCall(String addr, String method, String data, String mode){
        String receivedData = new String();
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
            //System.out.println("DEBUG: Response code of the object is " + code);
            switch(mode) {
                case "register":
                    if (code == 201) {
                        return "Registration successfull!";
                    } else {
                        return "Registration failed.";
                    }
                case "login":
                    if (code == 200) {
                        JsonParser jp = new JsonParser(); //from gson
                        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent())); //Convert the input stream to a json element
                        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
                        String id = rootobj.get("id").getAsString();
                        String authkey = rootobj.get("authkey").getAsString();
                        UserData.setId(Integer.parseInt(id));
                        UserData.setAuthkey(authkey);
                        //System.out.println("DEBUG: ID: "+id);
                        //System.out.println("DEBUG: Authkey: "+authkey);
                        return "Login OK";
                    } else {
                        return "Login failed";
                    }
                case "adverts":
                    if(code==200){
                        JsonParser jp = new JsonParser(); //from gson
                        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent())); //Convert the input stream to a json element
                        JsonObject rootobj = root.getAsJsonObject();
                        JsonArray rootary = rootobj.getAsJsonArray("content");

                        // Convert rootary to Advert array
                        Gson gson = new Gson();
                        Advert[] advertArray = gson.fromJson(rootary.toString(), Advert[].class);

                        for(Advert advert : advertArray) {
                            // Simplified output for adverts
                            if(simplified){
                                System.out.println(advert.getSimplified());
                            } else {
                                System.out.println(advert);
                            }
                        }
                    }
                case "addAdvert":
                    if (code == 200 || code == 201) {
                        return "Advertisement added!";
                    } else {
                        return "Failed to add advertisement.";
                    }
                case "modifyAdvert":
                    if(code == 200 || code == 202){
                        return "Content modified successfully!";
                    } else {
                        return "Failed to modify content. Response code: "+code;
                    }
                case "deleteAdvert":
                    if(code == 200 || code == 204){
                        return "Delete successfull!";
                    } else {
                        return "Failed to delete content. Response code: "+code;
                    }
                case "exchangeRate":
                    if(code == 200 || code == 201){
                        JsonParser jp = new JsonParser(); //from gson
                        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent())); //Convert the input stream to a json element
                        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
                        String price = rootobj.get("price").getAsString();
                        String result = String.format("Price: %s",price);
                        return result;
                    } else {
                        return "Failed to get exchange rate";
                    }
            }
            return connection.getContent().toString();
        } catch (IOException e){
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
        String response = apiCall(Config.SERVER_URL+"/login/", "POST", json.toString(), "login");
        return response;
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

        String response = apiCall(Config.SERVER_URL+"/users/", "POST", json.toString(), "register");
        return response;
    }

    public static String add_advert(String title, String content, String region, String category, String price){
        // Parse apiPost for adding advert
        // Create JSON Object
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("region", region);
        map.put("category", category);
        map.put("price", price);
        JSONObject json = new JSONObject(map);

        String response = apiCall(Config.SERVER_URL+"/users/"+UserData.getId()+"/advertisement", "POST", json.toString(), "addAdvert");
        return response;
    }

    public static String modify_advert(Integer advertisementId, String title, String content, String region, String category, String price){
        // Parse apiPost for adding advert
        // Create JSON Object
        // Create JSON Object
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("region", region);
        map.put("category", category);
        map.put("price", price);
        JSONObject json = new JSONObject(map);

        String response = apiCall(Config.SERVER_URL+"/users/"+UserData.getId()+"/advertisement/"+advertisementId+"/", "PUT", json.toString(), "modifyAdvert");
        return response;
    }

    public static String delete_advert(Integer advertisementId){
        String response = apiCall(Config.SERVER_URL+"/users/"+UserData.getId()+"/advertisement/"+advertisementId+"/", "DELETE", "", "deleteAdvert");
        return response;
    }

    public static String get_all_adverts(){
        String response = apiCall(Config.SERVER_URL+"/advertisement/", "GET", "", "adverts");
        return response;
    }

    public static String get_user_adverts(Integer userId){
        String response = apiCall(Config.SERVER_URL+"/users/"+UserData.getId()+"/advertisement/", "GET", "", "adverts");
        return response;
    }

    public static String getExchangeRate(Integer advert_id, String target_currency){
        String response = apiCall(Config.SERVER_URL+"/advertisement/"+advert_id+"/exchange/?targetCurrency="+target_currency, "GET", "", "exchangeRate");
        return response;
    }

}
