package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Img_Pojo {

    private String Image;

    private String Name;

    private Integer Price;

    private String Information;


    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
