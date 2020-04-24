package com.example.myapplication.GetProduct;

import com.example.myapplication.Product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getdata.php")
    Call<List<Product>> getAllProduct();
}
