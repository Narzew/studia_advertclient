package com.company.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {

    public static boolean validatePrice(String inputString) {
        Pattern compile = Pattern.compile("([0-9]+)(\\.[0-9]+)?([A-Z]{3})");
        Matcher matcher = compile.matcher(inputString);
        return matcher.matches();
    }

}
