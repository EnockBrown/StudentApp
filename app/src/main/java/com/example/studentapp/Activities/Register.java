package com.example.studentapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.storage.SharedPrefManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {
    EditText name,email,password,phone,admissionNumber,campus,faculty,gender;
    Button btnSignUp;
    TextView login;


    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(Register.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.edtName);
        email=findViewById(R.id.edtEmail);
        password=findViewById(R.id.edtPassword);
        phone=findViewById(R.id.edtPhone);
        admissionNumber=findViewById(R.id.edtAdmissionNumber);
        campus=findViewById(R.id.edtCampus);
        faculty=findViewById(R.id.edtFaculty);
        gender=findViewById(R.id.edtGender);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
                finish();
            }
        });

        btnSignUp=findViewById(R.id.btnsignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=name.getText().toString().trim();
                String em=email.getText().toString().trim();
                String pwd=password.getText().toString().trim();
                String phn=phone.getText().toString().trim();
                String adm=admissionNumber.getText().toString().trim();
                String cmp=campus.getText().toString().trim();
                String fclt=faculty.getText().toString().trim();
                String gen=gender.getText().toString().trim();

                if (em.isEmpty()){
                    email.setError("Email is Required");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }

                if (pwd.isEmpty()){
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }
                if (pwd.length()<6){
                    password.setError("Password should be atleast 6 characters long");
                    password.requestFocus();
                    return;
                }

                if (nm.isEmpty()){
                    name.setError("Name is Required or Empty");
                    name.requestFocus();
                    return;
                }

                if (phn.isEmpty()){
                    phone.setError("Phone is Required");
                    phone.requestFocus();
                    return;
                }

                if (adm.isEmpty()){
                    admissionNumber.setError("Admisiion Number is Required");
                    admissionNumber.requestFocus();
                    return;
                }

                if (cmp.isEmpty()){
                    campus.setError("Campus is Empty or  Required");
                    campus.requestFocus();
                    return;
                }

                if (fclt.isEmpty()){
                    faculty.setError("Faculty is Empty or Required");
                    faculty.requestFocus();
                    return;
                }

                if (gen.isEmpty()){
                    gender.setError("Gender is Required");
                    gender.requestFocus();
                    return;
                }
                //Do registration
                Toast.makeText(Register.this, adm, Toast.LENGTH_SHORT).show();
                String prg="mechanical";
                Call<ResponseBody> c=RetrofitClient.getInstance().getApi().createUser(
                        nm,em,pwd,gen,adm,prg,phn,cmp,fclt
                );

                c.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        String s= null;
                        try {
                            s = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
    }
}
