package com.example.studentapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.storage.SharedPrefManager;

public class HomeActivity extends AppCompatActivity {
    TextView name,admissionNumber,book_hostel,units;

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name=findViewById(R.id.name);
        admissionNumber=findViewById(R.id.admissionNumber);
        admissionNumber.setText(SharedPrefManager.getInstance(HomeActivity.this).getUser().getAdmissionNumber());
        name.setText(SharedPrefManager.getInstance(HomeActivity.this).getUser().getName());

        units=findViewById(R.id.view_units_results);
        units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Academics.class);
                startActivity(intent);
               // finish();
            }
        });

        book_hostel=findViewById(R.id.book_hostel);
        book_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,BookRoom.class);
                startActivity(intent);
               // finish();
            }
        });


    }
}
