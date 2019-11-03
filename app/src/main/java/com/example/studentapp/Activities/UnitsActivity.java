package com.example.studentapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.studentapp.R;
import com.example.studentapp.adapters.MyUnitsAdapter;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.models.Units;
import com.example.studentapp.models.UnitsResponse;
import com.example.studentapp.storage.SharedPrefManager;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnitsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Units> unitsList;
    private MyUnitsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        initToolbar();


        recyclerView=findViewById(R.id.recyclerview_my_units);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String id= String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());
        Call<UnitsResponse> call= RetrofitClient.getInstance()
                .getApi()
                .getMyRegisteredUnits(id);
        call.enqueue(new Callback<UnitsResponse>() {
            @Override
            public void onResponse(Call<UnitsResponse> call, Response<UnitsResponse> response) {
                unitsList=response.body().getUnits();
                adapter=new MyUnitsAdapter(UnitsActivity.this,unitsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UnitsResponse> call, Throwable t) {

            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar=findViewById(R.id.results);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Units");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
