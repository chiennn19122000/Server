package com.example.myapplication.SendDataToServer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient  {

    @Multipart
    @POST("uploadimageproduct.php")
    Call<String> UpLoadImage(@Part MultipartBody.Part image);
}
