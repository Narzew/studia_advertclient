package com.company.data;

import java.util.ArrayList;

public class AdvertData {

    public static ArrayList<Advert> adverts = new ArrayList<Advert>();

    public static ArrayList<Advert> getAdverts() {
        return adverts;
    }

    public static void setAdverts(ArrayList<Advert> adverts) {
        AdvertData.adverts = adverts;
    }
}
