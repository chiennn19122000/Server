package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Img_Pojo {

    @SerializedName("image")
    private String Image;

    @SerializedName("product_name")
    private String Name;

    @SerializedName("product_price")
    private Integer Price;

    @SerializedName("product_information")
    private String Information;


    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
