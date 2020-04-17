package com.example.myapplication.FragmentSystem;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.myapplication.SendDataToServer.APIUtils;
import com.example.myapplication.SendDataToServer.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends Fragment {

    @Nullable

    private int request_code = 123;
    String realpath = "";
    ImageView addImageProduct;
    EditText addNameProduct,addPriceProduct,addInformationProduct;
    Button post;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product,container,false);

        addImageProduct = (ImageView) view.findViewById(R.id.add_image_product);
        addNameProduct = (EditText) view.findViewById(R.id.name_product);
        addPriceProduct = (EditText) view.findViewById(R.id.price_product);
        addInformationProduct = (EditText) view.findViewById(R.id.information_product);
        post = (Button) view.findViewById(R.id.post_add);

        SetImage();
        UpLoadData();

        return view;
    }
    private void SetImage() {
        addImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,request_code);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                addImageProduct.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void UpLoadData()
    {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (realpath == "")
                {
                    Toast.makeText(getActivity(),"loi",Toast.LENGTH_SHORT).show();
                }
                else {
                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath();

                    String[] nameimage = file_path.split("\\.");
                    file_path = nameimage[0] + System.currentTimeMillis() + "." + nameimage[1];

                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",file_path,requestBody);

                    DataClient dataClient = APIUtils.getData();
                    retrofit2.Call<String> callback = dataClient.UpLoadImage(body);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                            if (response != null)
                            {
                                String message = response.body();
                                Log.d("bbb",message);
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<String> call, Throwable t) {
                            Log.d("bbb",t.getMessage());
                        }
                    });

                }
            }
        });
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
