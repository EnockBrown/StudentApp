package com.example.studentapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.storage.SharedPrefManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings extends AppCompatActivity {
    EditText name,email,phone,admissionNumber,campus,faculty,gender;
    Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolbar();

        name=findViewById(R.id.edtName);
        email=findViewById(R.id.edtEmail);
        phone=findViewById(R.id.edtPhone);
        admissionNumber=findViewById(R.id.edtAdmissionNumber);
        campus=findViewById(R.id.edtCampus);
        faculty=findViewById(R.id.edtFaculty);
        gender=findViewById(R.id.edtGender);

        name.setText(SharedPrefManager.getInstance(this).getUser().getName());
        email.setText(SharedPrefManager.getInstance(this).getUser().getEmail());
        phone.setText(SharedPrefManager.getInstance(this).getUser().getPhone());
        admissionNumber.setText(SharedPrefManager.getInstance(this).getUser().getAdmissionNumber());
        campus.setText(SharedPrefManager.getInstance(this).getUser().getCampus());
        faculty.setText(SharedPrefManager.getInstance(this).getUser().getFaculty());
        gender.setText(SharedPrefManager.getInstance(this).getUser().getGender());





        btnupdate=findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Updated", Toast.LENGTH_SHORT).show();
                String id=String.valueOf(SharedPrefManager.getInstance(Settings.this).getUser().getUnique_id());
                String uname=name.getText().toString().trim();
                String uemail=email.getText().toString().trim();
                String uphone=phone.getText().toString().trim();
                String uadm=admissionNumber.getText().toString().trim();
                String ucampus=campus.getText().toString().trim();
                String ufaculty=faculty.getText().toString().trim();
                String ugender=gender.getText().toString().trim();

                Call<ResponseBody> call= RetrofitClient.getInstance().getApi().update_user(
                        id,uname,uemail,uphone,uadm,ucampus,ufaculty,ugender
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String s=  response.body().string();
                            Toast.makeText(Settings.this, s, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Settings.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar=findViewById(R.id.results);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
