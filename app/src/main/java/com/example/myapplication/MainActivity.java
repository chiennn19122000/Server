package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.example.myapplication.Constants.BaseUrlUpload;
import static com.example.myapplication.Constants.userConst;

public class MainActivity extends BaseActivity {
    @BindView(R.id.userServer)
    EditText user;

    @BindView(R.id.passServer)
    EditText pass;

    @BindView(R.id.login)
    Button login;

//    @BindView(R.id.imagexxx)
//    ImageView iamge;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupListener() {
        Login();
//        Picasso.with(MainActivity.this).load(BaseUrlUpload +"Uploads/aa.jpg").error(R.drawable.imagegridview1).into(iamge);

    }

    @Override
    protected void populateData() {
        dialog();
        HideTitle();
    }

    private void Login()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("file_login",MODE_PRIVATE);
                if (user.getText().toString().equals(userConst)&&pass.getText().toString().equals(preferences.getString("pass","")))
                {
                    startActivity(new Intent(MainActivity.this,SystemActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dialog() //kiểm tra lần đầu tiên mở app
    {
        SharedPreferences preferences = getSharedPreferences("file_login",MODE_PRIVATE);
        boolean check = preferences.getBoolean("check_dialog", true);

        if(check)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Tài khoản đăng nhập mặc định là: admin \n" +
                    "Mật khẩu lần đầu đăng nhập là : admin \n" +
                    "Sau khi đăng nhập hãy đổi lại mật khẩu, nếu không lần sau đăng nhập vẫn mặc định là: admin" );
            builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DataLogin();
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    private void DataLogin()
    {
        SharedPreferences preferences = getSharedPreferences("file_login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pass","admin");
        editor.putBoolean("check_dialog",false );
        editor.commit();
    }
}
