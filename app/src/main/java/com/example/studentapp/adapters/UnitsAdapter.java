package com.example.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.models.Units;

import java.util.List;

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
                Toast.makeText(context, units.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitsList.size();
    }

    class UnitsViewHolder extends RecyclerView.ViewHolder{

        TextView code,name;
        ImageView add;
        public UnitsViewHolder(@NonNull View itemView) {
            super(itemView);

            code=itemView.findViewById(R.id.code);
            name=itemView.findViewById(R.id.name);
            add=itemView.findViewById(R.id.add_unit);
        }
    }
    }

