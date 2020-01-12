package com.company.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advert {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("price")
    @Expose
    public String price;

    public String toString(){
        return String.format("Advert #%d: %s\nContent: %s\nRegion: %s\nPrice: %s\n", id, title, content, region, price);
    }

}
