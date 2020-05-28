package com.example.myapplication.SendDataToServer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("upload.php")
    Call<Img_Pojo> uploadImage(@Field("product_name") String name, @Field("product_price") Integer price, @Field("product_information") String information,@Field("image") String image);

    @FormUrlEncoded
    @POST("modify.php")
    Call<Modify> modifyProduct(@Field("product_id") Integer id, @Field("product_name") String name ,@Field("product_price") Integer price, @Field("product_information") String information, @Field("image") String image,@Field("delete_image") String deleteimage);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Delete> deleteProduct(@Field("product_id") Integer id,@Field("image") String image);

    @FormUrlEncoded
    @POST("addseller.php")
    Call<Seller> addseller(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("sdt") int sdt , @Field("email") String email,@Field("place") String place);

}
