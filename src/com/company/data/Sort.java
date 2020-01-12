package com.company.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sort {

    @SerializedName("unsorted")
    @Expose
    public Boolean unsorted;
    @SerializedName("sorted")
    @Expose
    public Boolean sorted;
    @SerializedName("empty")
    @Expose
    public Boolean empty;

}
