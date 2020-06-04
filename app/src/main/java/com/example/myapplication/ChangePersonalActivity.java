package com.example.myapplication;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Img_Pojo;
import com.example.myapplication.SendDataToServer.InfoAdmin;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constants.BaseUrlUpload;

public class ChangePersonalActivity extends BaseActivity {
    @BindView(R.id.change_name_company)
    EditText changname;

    @BindView(R.id.change_sdt_company)
    EditText changesdt;

    @BindView(R.id.change_email_company)
    EditText changeemail;

    @BindView(R.id.save_change_company)
    Button save;

    String name,sdt,email;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void setupListener() {
        Save();
    }

    @Override
    protected void populateData() {
        callback();
        setTitle("Thay đổi thông tin");
    }
    private void Save()
    {
        SharedPreferences preferences = getSharedPreferences("data_admin",MODE_PRIVATE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(changname.getText().toString()))
                {
                    name = preferences.getString("name","");
                }
                else
                {
                    name = changname.getText().toString();
                }
                if (TextUtils.isEmpty(changesdt.getText().toString()))
                {
                    sdt = preferences.getString("sdt","");
                }
                else
                {
                    sdt = changesdt.getText().toString();
                }
                if (TextUtils.isEmpty(changeemail.getText().toString()))
                {
                    email = preferences.getString("email","");
                }
                else
                {
                    email = changeemail.getText().toString();
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",name);
                editor.putString("sdt",sdt);
                editor.putString("email",email);
                editor.commit();
                UpDateInfo();
                Toast.makeText(ChangePersonalActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void UpDateInfo() {

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlUpload).create(ApiInterface.class);
        Call<InfoAdmin> call = apiInterface.updateinfo( name,Integer.valueOf(sdt),email);

        call.enqueue(new Callback<InfoAdmin>() {
            @Override
            public void onResponse(Call<InfoAdmin> call, Response<InfoAdmin> response) {

                InfoAdmin infoAdmin = response.body();
                Log.d("Server Response",""+infoAdmin.getResponse());

            }

            @Override
            public void onFailure(Call<InfoAdmin> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }
}
