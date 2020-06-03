package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class InfoAdmin {

    @SerializedName("name_company")
    private String name;

    @SerializedName("sdt_company")
    private Integer sdt;

    @SerializedName("email_company")
    private String email;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
