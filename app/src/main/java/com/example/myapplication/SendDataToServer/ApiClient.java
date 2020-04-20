package com.example.myapplication.SendDataToServer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.Constants.BaseUrl;

public class ApiClient {


    private static Retrofit retrofit;

    public static Retrofit getApiClient() {

        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
