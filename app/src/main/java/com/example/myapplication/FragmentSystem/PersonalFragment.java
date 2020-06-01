package com.example.myapplication.FragmentSystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ActivityChangePass;
import com.example.myapplication.ChangePersonalActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import static android.content.Context.MODE_PRIVATE;

public class PersonalFragment extends Fragment {
    TextView companyname,companynumberphone,companyemail;
    Button changeinfo,changepass,logout,exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        companyname = (TextView) view.findViewById(R.id.company_name);
        companynumberphone = (TextView) view.findViewById(R.id.company_number_phone);
        companyemail = (TextView) view.findViewById(R.id.company_email);
        changeinfo = (Button) view.findViewById(R.id.thay_doi_thong_tin);
        changepass = (Button) view.findViewById(R.id.thay_doi_pass);
        logout = (Button) view.findViewById(R.id.logout);
        exit = (Button) view.findViewById(R.id.exit);
        setData();
        ChangeInfo();
        ChangePass();
        Logout();
        Exit();

        return view;
    }

    private void ChangePass() {
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityChangePass.class));
            }
        });
    }

    private void Exit() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Question ?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("are you sure you want to exit");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    private void Logout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    private void ChangeInfo() {
        changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePersonalActivity.class));
            }
        });
    }

    private void setData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data_admin",MODE_PRIVATE);
        companyname.setText("Tên công ty: " + preferences.getString("name",""));
        companynumberphone.setText("Số điện thoại: " + preferences.getString("sdt",""));
        companyemail.setText("Email: " + preferences.getString("email",""));
    }
}
