package com.example.bloodbank;

import com.google.gson.annotations.SerializedName;

public class DeletePojo {

    @SerializedName("result")
    private String result;

    public DeletePojo(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
