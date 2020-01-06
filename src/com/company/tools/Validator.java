package com.company.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

     /*
    Validate e-mail address
     */

    public static Boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /*
    Validate phone number
     */

    public static Boolean validatePhoneNumber(String s)
    {
        Pattern p = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}
