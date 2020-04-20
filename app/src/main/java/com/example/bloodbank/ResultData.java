package com.example.bloodbank;

import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("name")
    private String name;
    @SerializedName("contact")
    private String contact;
    @SerializedName("blood")
    private String blood;
    @SerializedName("city")
    private String city;
    @SerializedName("result")
    private String result;

    public ResultData(String name, String contact, String blood, String city, String result) {
        this.name = name;
        this.contact = contact;
        this.blood = blood;
        this.city = city;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
