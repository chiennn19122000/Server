package com.example.myapplication.SendDataToServer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("upload.php")
    Call<Img_Pojo> uploadImage(@Field("image") String image, @Field("product_name") String name, @Field("product_price") Integer price, @Field("product_information") String information);
}
