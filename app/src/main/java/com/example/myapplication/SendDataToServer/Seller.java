package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Seller implements Serializable {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private int sdt;

    @SerializedName("email")
    private String email;

    @SerializedName("place")
    private String place;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public Seller(String username, String password, String name, int sdt, String email, String place) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Tên: " + getName() + "\n" +
                "    SĐT: " + getSdt() + "\n"+
                "    Email: " + getEmail() ;
    }
}