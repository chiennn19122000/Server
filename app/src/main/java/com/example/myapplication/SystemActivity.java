package com.example.myapplication;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.myapplication.FragmentSystem.AddProductFragment;
import com.example.myapplication.FragmentSystem.ManagerSeller;
import com.example.myapplication.FragmentSystem.PersonalFragment;
import com.example.myapplication.FragmentSystem.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class SystemActivity extends BaseActivity {

    @BindView(R.id.mainbar)
    BottomNavigationView MainBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_system;
    }

    @Override
    protected void setupListener() {
        ListenItemBar(); // set fragment đầu tiên khi đăng nhập
    }

    @Override
    protected void populateData() {

    }
    private void ListenItemBar()
    {
        setTitle("Các sản phẩm đang bán");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductFragment()).commit();
        MainBar.setOnNavigationItemSelectedListener(mainbarListen);
    }
// hàm để chuyển frag ment khi chọn các item màn hình chính
    private BottomNavigationView.OnNavigationItemSelectedListener mainbarListen =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home_product:
                            setTitle("Các sản phẩm đang bán");
                            selectedFragment = new ProductFragment();
                            break;
                        case R.id.post_add:
                            setTitle("Đăng sản phẩm");
                            selectedFragment = new AddProductFragment();
                            break;
                        case R.id.order:
                            setTitle("Quản lí người bán");
                            selectedFragment = new ManagerSeller();
                            break;
                        case R.id.personal:
                            setTitle("Thông tin cá nhân");
                            selectedFragment = new PersonalFragment();
                            break;
                    }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                    return true;
                }

            };

    private void setTitle(String s)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);

    }

}