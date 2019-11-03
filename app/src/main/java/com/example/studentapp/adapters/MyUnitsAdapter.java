package com.example.studentapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.models.Units;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyUnitsAdapter extends RecyclerView.Adapter<MyUnitsAdapter.MyUnitsViewHolder> {

    private Context context;
    private List<Units> unitsList;

    public MyUnitsAdapter(Context context, List<Units> unitsList) {
        this.context = context;
        this.unitsList = unitsList;
    }

    @NonNull
    @Override
    public MyUnitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.my_units, parent,false);
        return  new MyUnitsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUnitsViewHolder holder, int position) {
        final Units units=unitsList.get(position);
        holder.name.setText(units.getName());
        holder.code.setText(units.getCode());
        holder.id.setText(String.valueOf(units.getUnique_id()));
        holder.drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Confirm Remove this Unit? ");
                alert.setMessage(units.getCode()+" "+ units.getName());
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id=String.valueOf( units.getUnique_id());

                        Call<ResponseBody> call= RetrofitClient.getInstance().getApi().drop_unit(id);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                               // Toast.makeText(context, units.getId(), Toast.LENGTH_SHORT).show();
                                try {
                                    String s = response.body().string();
                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=alert.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitsList.size();
    }

    class MyUnitsViewHolder extends RecyclerView.ViewHolder{
        TextView code,name,drop,id;
        public MyUnitsViewHolder(@NonNull View itemView) {
            super(itemView);

            code=itemView.findViewById(R.id.code);
            name=itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.id);
            drop=itemView.findViewById(R.id.drop_unit);
        }
    }

}
