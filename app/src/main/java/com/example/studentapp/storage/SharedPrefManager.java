package com.example.studentapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentapp.models.User;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context context;
    private static final String SHARED_PREF_NAME="my_shared_pref_name";

    private SharedPrefManager(Context context){
        this.context=context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("name",user.getName());
        editor.putString("email",user.getEmail());
        editor.putString("gender",user.getGender());
        editor.putString("admissionNumber",user.getAdmissionNumber());
        editor.putString("phone",user.getPhone());
        editor.putString("campus",user.getCampus());
        editor.putString("faculty",user.getFaculty());

        editor.apply();

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1)!=-1;
    }

    public  User getUser(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("gender",null),
                sharedPreferences.getString("admissionNumber",null),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("campus",null),
                sharedPreferences.getString("faculty",null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
