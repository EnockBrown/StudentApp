package com.example.studentapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.R;
import com.example.studentapp.api.RetrofitClient;
import com.example.studentapp.models.Units;
import com.example.studentapp.storage.SharedPrefManager;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitsViewHolder> {

    private Context context;
    private List<Units> unitsList;

    public UnitsAdapter(Context context, List<Units> unitsList) {
        this.context = context;
        this.unitsList = unitsList;
    }

    @NonNull
    @Override
    public UnitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_units,parent,false);
       return new UnitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitsViewHolder holder, int position) {
        final Units units=unitsList.get(position);
        holder.code.setText(units.getCode());
        holder.name.setText(units.getName());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, units.getCode(), Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Confirm Enrollment for ");
                alert.setMessage(units.getCode()+" "+ units.getName());
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String frm = String.valueOf(SharedPrefManager.getInstance(context).getUser().getId());
                        String unit_nm =units.getName();
                        String unit_cd = units.getCode();

                        String adm = SharedPrefManager.getInstance(context).getUser().getAdmissionNumber();
                        String fct = SharedPrefManager.getInstance(context).getUser().getFaculty();
                        String con = SharedPrefManager.getInstance(context).getUser().getPhone();
                        Call<ResponseBody> call= RetrofitClient.getInstance().getApi().unit_register(
                                frm,unit_nm,unit_cd,adm,fct,con
                        );

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                //String s=response.body().string();
                                Toast.makeText(context, " Enrolled  Successfully ", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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

    class UnitsViewHolder extends RecyclerView.ViewHolder{
        TextView code,name;
        Button add;
        public UnitsViewHolder(@NonNull View itemView) {
            super(itemView);

            code=itemView.findViewById(R.id.code);
            name=itemView.findViewById(R.id.name);
            add=itemView.findViewById(R.id.add_unit);
        }
    }
    }

