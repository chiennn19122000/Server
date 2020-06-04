package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Modify {

    @SerializedName("product_id")
    private Integer Id;

    @SerializedName("product_name")
    private String Name;

    @SerializedName("product_price")
    private Integer Price;

    @SerializedName("product_information")
    private String Information;

    @SerializedName("image")
    private String Image;

    @SerializedName("delete_image")
    private String deleteimage;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
