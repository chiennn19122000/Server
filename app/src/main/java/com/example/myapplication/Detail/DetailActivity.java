package com.example.myapplication.Detail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.Product.Product;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Delete;
import com.example.myapplication.SendDataToServer.Modify;
import com.example.myapplication.SystemActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constants.BaseUrlUpload;
import static com.example.myapplication.Constants.REQUEST_CODE;
import static com.example.myapplication.Constants.SEND_DATA;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.show_product)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.info)
    TextView info;

    @BindView(R.id.change_image)
    Button changeimage;
    @BindView(R.id.change_name)
    Button changename;
    @BindView(R.id.change_price)
    Button changeprice;
    @BindView(R.id.change_info)
    Button changeinfo;
    @BindView(R.id.save_change)
    Button savechange;
    @BindView(R.id.delete)
    Button delete;

    Bitmap bitmap;

    Integer id1;
    String deleteImage;
    Boolean checkBitMap = true;

    
    @Override
    protected int getLayoutRes() {
        return R.layout.modify_product;
    }

    @Override
    protected void setupListener() {
        ChangeImage();
        ChangeName();
        ChangePrice();
        ChangeInfo();
        Save();
        DeletePro();
    }

    @Override
    protected void populateData() {
        SetData();
        callback();
        setTitle("Thông tin sản phẩm");
    }

    private void SetData() {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(SEND_DATA);
        Picasso.with(DetailActivity.this).load(BaseUrlUpload+product.getImage()).into(image);
        if (checkBitMap)
        {
            bitmap = getBitmapFromURL(BaseUrlUpload+product.getImage());
        }
        name.setText(product.getName());
        price.setText(product.getPrice());
        info.setText(product.getInformation());
        id1 = Integer.valueOf(product.getId());
        deleteImage = product.getImage();

        
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void ChangeName()
    {
        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contentChangeName();
            }
        });
    }
    private void contentChangeName()
    {
        Dialog dialog = new Dialog(DetailActivity.this);
        dialog.setTitle("Đổi Tên");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change);

        EditText editText = dialog.findViewById(R.id.edit);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button ok = dialog.findViewById(R.id.ok);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(DetailActivity.this,"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    name.setText(editText.getText().toString());
                    dialog.cancel();
                }
            }
        });


        dialog.show();
    }
    private void ChangePrice()
    {
        changeprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentChangePrice();
            }
        });
    }
    private void contentChangePrice()
    {
        Dialog dialog = new Dialog(DetailActivity.this);
        dialog.setTitle("Đổi Giá");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change);

        EditText editText = dialog.findViewById(R.id.edit);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button ok = dialog.findViewById(R.id.ok);

        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(DetailActivity.this,"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    price.setText(editText.getText().toString());
                    dialog.cancel();
                }
            }
        });


        dialog.show();
    }
    private void ChangeInfo()
    {
        changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentChangeInfo();
            }
        });
    }
    private void contentChangeInfo()
    {
        Dialog dialog = new Dialog(DetailActivity.this);
        dialog.setTitle("Đổi Mô Tả");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change);

        EditText editText = dialog.findViewById(R.id.edit);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button ok = dialog.findViewById(R.id.ok);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(DetailActivity.this,"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    info.setText(editText.getText().toString());
                    dialog.cancel();
                }
            }
        });


        dialog.show();
    }

    private void ChangeImage()
    {
        changeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBitMap = false;
                selectImage();
            }
        });
    }

    private void Save()
    {
        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyProduct();
            }
        });
    }

    private void ModifyProduct()
    {

        String image1 = convertToString(bitmap);
//        String image1 = image.getDrawable().toString();
        String name1 = name.getText().toString();
        Integer price1 = Integer.parseInt(price.getText().toString());
        String information1 = info.getText().toString();
        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlUpload).create(ApiInterface.class);
        Call<Modify> call = apiInterface.modifyProduct( id1,name1,price1,information1, image1,deleteImage);

        call.enqueue(new Callback<Modify>() {
            @Override
            public void onResponse(Call<Modify> call, Response<Modify> response) {

                Modify modify = response.body();
                Log.d("Server Response",""+modify.getResponse());
                Toast.makeText(DetailActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailActivity.this, SystemActivity.class));

            }

            @Override
            public void onFailure(Call<Modify> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }

    private void DeletePro()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteProduct();
            }
        });
    }

    private void DeleteProduct()
    {
        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlUpload).create(ApiInterface.class);
        Call<Delete> call = apiInterface.deleteProduct( id1,deleteImage);

        call.enqueue(new Callback<Delete>() {
            @Override
            public void onResponse(Call<Delete> call, Response<Delete> response) {

                Delete delete = response.body();
                Log.d("Server Response",""+delete.getResponse() + deleteImage);
                Toast.makeText(DetailActivity.this,"Đã xóa sản phẩm",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailActivity.this, SystemActivity.class));


            }

            @Override
            public void onFailure(Call<Delete> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
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
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
