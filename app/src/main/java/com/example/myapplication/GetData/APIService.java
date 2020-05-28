package com.example.myapplication.GetData;

import com.example.myapplication.Product.Product;
import com.example.myapplication.SendDataToServer.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getdata.php")
    Call<List<Product>> getAllProduct();

    @GET("getseller.php")
    Call<List<Seller>> getAllSeller();
}
