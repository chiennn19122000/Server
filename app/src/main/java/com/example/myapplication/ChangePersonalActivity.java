package com.example.myapplication;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.BindView;

public class ChangePersonalActivity extends BaseActivity {
    @BindView(R.id.change_name_company)
    EditText changname;

    @BindView(R.id.change_sdt_company)
    EditText changesdt;

    @BindView(R.id.change_email_company)
    EditText changeemail;

    @BindView(R.id.save_change_company)
    Button save;
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(changname.getText().toString()))
                {
                    Toast.makeText(ChangePersonalActivity.this,"Bạn chưa nhập tên",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (TextUtils.isEmpty(changesdt.getText().toString()))
                    {
                        Toast.makeText(ChangePersonalActivity.this,"Bạn chưa nhập số điện thoại",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (TextUtils.isEmpty(changeemail.getText().toString()))
                        {
                            Toast.makeText(ChangePersonalActivity.this,"Bạn chưa nhập email",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            SharedPreferences preferences = getSharedPreferences("data_admin",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("name",changname.getText().toString());
                            editor.putString("sdt",changesdt.getText().toString());
                            editor.putString("email",changeemail.getText().toString());
                            editor.commit();
                            Toast.makeText(ChangePersonalActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }
}
