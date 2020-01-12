package com.company.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories {
    private static final List<String> categories = new ArrayList<String>(
            Arrays.asList("Automotive",
                    "Real Estate",
                    "Job",
                    "House and garden",
                    "Electronics",
                    "Fashion",
                    "Agriculture",
                    "Animals",
                    "For children",
                    "Sport and hobby",
                    "Music and education",
                    "Services and companies",
                    "Wedding and reception"));

    public static boolean isCategoryValid(String category) {
        return categories.contains(category);
    }

    public static String getCategoryStr(){
        return categories.toString();
    }

}
