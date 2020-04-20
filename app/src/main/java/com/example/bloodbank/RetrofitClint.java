package com.example.bloodbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {
    public static Retrofit retrofit;
    public static final String BASE_URL = "http://10.0.3.2/bloodbank/";
    public static RetrofitClint instance;
    private RetrofitClint(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClint getInstance(){
        if (retrofit == null){
            instance = new RetrofitClint();
            return  instance;
        }
        return instance;
    }
    public static MyApi getApi(){
        return retrofit.create(MyApi.class);
    }
}
