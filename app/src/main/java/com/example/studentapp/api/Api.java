package com.example.studentapp.api;

import com.example.studentapp.models.LoginResponse;
import com.example.studentapp.models.UnitsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
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


    //drop/deregister units
    @DELETE("drop_unit/{unique_id}")
    Call<ResponseBody> drop_unit(
            @Path("unique_id")String unique_id);

    //GET REGISTRED UNITS FOR A PSRTICULAR STUDENT
    @GET("my_units/{from_users_id}")
    Call<UnitsResponse> getMyRegisteredUnits(
            @Path("from_users_id") String from_users_id);


    //unit_registration
    @FormUrlEncoded
    @POST("unit_enrollment")
    Call<ResponseBody> unit_register(
            @Field("from") String from,
            @Field("unit") String unit,
            @Field("unit_code") String unit_code,
            @Field("admissionNumber") String admissionNumber,
            @Field("faculty") String faculty,
            @Field("contacts") String contacts

    );
}
