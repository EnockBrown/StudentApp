package com.example.studentapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentapp.Activities.UnitsActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapters.UnitsAdapter;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.models.Units;
import com.example.studentapp.models.UnitsResponse;
import com.example.studentapp.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnitsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Units>unitsList;
    private UnitsAdapter adapter;
    private TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_units, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<UnitsResponse> call= RetrofitClient.getInstance().getApi().getUnits(SharedPrefManager.getInstance(getActivity()).getUser().getFaculty());
        call.enqueue(new Callback<UnitsResponse>() {
            @Override

            public void onResponse(Call<UnitsResponse> call, Response<UnitsResponse> response) {
                unitsList=response.body().getUnits();
                adapter=new UnitsAdapter(getActivity(),unitsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UnitsResponse> call, Throwable t) {

            }
        });

        txt=view.findViewById(R.id.view_your_registered_units);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(), UnitsActivity.class);
                startActivity(i);
            }
        });
    }
}
