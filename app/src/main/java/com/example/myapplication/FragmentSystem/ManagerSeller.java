package com.example.myapplication.FragmentSystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.AddSellerActivity;
import com.example.myapplication.GetData.APIService;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.Seller;
import com.example.myapplication.Sumpay.SumPayment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.Constants.BaseUrlGet;

public class ManagerSeller extends Fragment {

    ListView lv;
    TextView sumMoney;
    Button addSeller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_seller,container,false);
        lv = (ListView) view.findViewById(R.id.lv);
        sumMoney = (TextView) view.findViewById(R.id.sum_money);
        addSeller = (Button) view.findViewById(R.id.add_seller);

        setData();
        AddSeller();
        return view;
    }

    private void AddSeller() {

        addSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSellerActivity.class));
            }
        });
    }

    private void setData() {
        setListSeller();
        setRevenue();


    }
    private void setListSeller()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Seller>> call = apiService.getAllSeller();
        call.enqueue(new Callback<List<Seller>>() {
            @Override
            public void onResponse(Call<List<Seller>> call, Response<List<Seller>> response) {
                List<Seller> sellerList = response.body();
                int t = sellerList.size();
                String[] arr= new String[t];
                for (int i = 0; i<sellerList.size() ; i++) {
                    arr[i] = String.valueOf(i+1) + " " + sellerList.get(i).toString();
                    Log.d(TAG, "onResponse" + sellerList.get(i).toString());
                }
                ArrayAdapter adapter= new ArrayAdapter(getActivity(),android.R.layout.simple_expandable_list_item_1,arr);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Seller>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setRevenue()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<SumPayment>> call = apiService.getSumPayment();
        call.enqueue(new Callback<List<SumPayment>>() {
            @Override
            public void onResponse(Call<List<SumPayment>> call, Response<List<SumPayment>> response) {
                List<SumPayment> sellerList = response.body();
                for (int i = 0; i<sellerList.size() ; i++) {

                    Log.d(TAG, "onResponse" + sellerList.get(i).getSum());
                }
                sumMoney.setText("Tổng doanh thu: " + sellerList.get(0).getSum() + " VNĐ");
            }

            @Override
            public void onFailure(Call<List<SumPayment>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
