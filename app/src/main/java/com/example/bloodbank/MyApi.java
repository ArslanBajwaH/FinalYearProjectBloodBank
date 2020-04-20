package com.example.bloodbank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {

    @FormUrlEncoded
    @POST("register.php")
    Call<Pojo> register(
            @Field("name") String name,
            @Field("contact") String contact,
            @Field("password") String password,
            @Field("email") String email,
            @Field("city") String city,
            @Field("blood") String blood
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Pojo> login(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @FormUrlEncoded
    @POST("result.php")
    Call<List<ResultData>> getResults(
            @Field("city") String city,
            @Field("bloodgroup") String bgroup
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<DeletePojo> deleteMyAccount(
            @Field("email") String email
    );
}
