package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Delete {
    @SerializedName("product_id")
    private Integer Id;

    @SerializedName("image")
    private String image;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
