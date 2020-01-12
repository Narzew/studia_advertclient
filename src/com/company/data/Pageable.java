package com.company.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pageable {

    @SerializedName("sort")
    @Expose
    public Sort sort;
    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("pageSize")
    @Expose
    public Integer pageSize;
    @SerializedName("pageNumber")
    @Expose
    public Integer pageNumber;
    @SerializedName("unpaged")
    @Expose
    public Boolean unpaged;
    @SerializedName("paged")
    @Expose
    public Boolean paged;

}
