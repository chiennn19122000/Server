package com.example.myapplication.Sumpay;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SumPayment implements Serializable {
    @SerializedName("sum")
    private Integer sum;

    public SumPayment(Integer sum) {
        this.sum = sum;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
