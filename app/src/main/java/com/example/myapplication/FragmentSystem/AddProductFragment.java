package com.example.myapplication.FragmentSystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Img_Pojo;
import com.example.myapplication.SystemActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.myapplication.Constants.BaseUrlUpload;
import static com.example.myapplication.Constants.REQUEST_CODE;

public class AddProductFragment extends Fragment {

    @Nullable

    ImageView addImageProduct;
    EditText addNameProduct,addPriceProduct,addInformationProduct;
    Button post,selectimv;
    Bitmap bitmap;
    Boolean x = false;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product,container,false);

        addImageProduct = (ImageView) view.findViewById(R.id.add_image_product);
        addNameProduct = (EditText) view.findViewById(R.id.name_product);
        addPriceProduct = (EditText) view.findViewById(R.id.price_product);
        addInformationProduct = (EditText) view.findViewById(R.id.information_product);
        post = (Button) view.findViewById(R.id.post_add);
        selectimv = (Button) view.findViewById(R.id.select);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.addimageproduct);


        Select();
        Post();




        return view;
    }
    private void Select()
    {
        selectimv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void Post()
    {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addNameProduct.getText().toString().equals(""))
                {
                    x = false;
                    Toast.makeText(getActivity(),"Nhập tên cho sản phẩm",Toast.LENGTH_LONG).show();
                }
                else {
                    if (addPriceProduct.getText().toString().equals(""))
                    {
                        x = false;
                        Toast.makeText(getActivity(),"Nhập giá cho sản phẩm",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (addInformationProduct.getText().toString().equals(""))
                        {
                            x = false;
                            Toast.makeText(getActivity(),"Nhập thông tin cho sản phẩm",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            x = true;
                        }
                    }
                }
                if (x)
                {
                    uploadImage();
                }
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private String convertToString(Bitmap bitmap1)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                addImageProduct.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){

        String image = convertToString(bitmap);
        String name = addNameProduct.getText().toString();
        Integer price = Integer.parseInt(addPriceProduct.getText().toString());
        String information = addInformationProduct.getText().toString();
        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlUpload).create(ApiInterface.class);
        Call<Img_Pojo> call = apiInterface.uploadImage( name,price,information, image);

        call.enqueue(new Callback<Img_Pojo>() {
            @Override
            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {

                Img_Pojo img_pojo = response.body();
                Log.d("Server Response",""+img_pojo.getResponse());

            }

            @Override
            public void onFailure(Call<Img_Pojo> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });

        addImageProduct.setImageResource(R.drawable.addimageproduct);
        addNameProduct.setText("");
        addPriceProduct.setText("");
        addInformationProduct.setText("");
        Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
    }
}
