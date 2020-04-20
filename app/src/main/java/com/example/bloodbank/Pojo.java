package com.example.bloodbank;

import com.google.gson.annotations.SerializedName;

public class Pojo {
    @SerializedName("result")
    private String result;
    @SerializedName("name")
    private String name;
    @SerializedName("contact")
    private String contact;
    @SerializedName("city")
    private String city;
    @SerializedName("blood")
    private String blood;
    @SerializedName("email")
    private String email;
    @SerializedName("pass")
    private String pass;

    public Pojo(String result, String name, String contact, String city, String blood, String email, String pass) {
        this.result = result;
        this.name = name;
        this.contact = contact;
        this.city = city;
        this.blood = blood;
        this.email = email;
        this.pass = pass;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
