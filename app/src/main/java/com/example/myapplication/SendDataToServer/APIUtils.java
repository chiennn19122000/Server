package com.example.myapplication.SendDataToServer;

import static com.example.myapplication.Constants.Base_Url;

public class APIUtils {
    // class cung cấp đường dẫn

    public static DataClient getData()
    {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}

