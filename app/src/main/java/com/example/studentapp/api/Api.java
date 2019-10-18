package com.example.studentapp.api;

import com.example.studentapp.models.LoginResponse;
import com.example.studentapp.models.UnitsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("admissionNumber") String admissionNumber,
            @Field("phone") String phone,
            @Field("campus") String campus,
            @Field("faculty") String faculty

    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("mysubjects/{faculty}")
    Call<UnitsResponse> getUnits (
            @Path("faculty") String faculty);
}
