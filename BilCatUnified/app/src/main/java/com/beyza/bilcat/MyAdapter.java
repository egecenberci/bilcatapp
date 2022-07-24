package com.beyza.bilcat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<CatData> list;

    public MyAdapter(Context context, ArrayList<CatData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.catlistcard, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CatData catData = list.get(position);
        holder.txtCatName.setText(catData.getName());
        holder.txtCatNighbourhood.setText(catData.getNeighbourhood());
        holder.txtCatAge.setText(catData.getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCatName, txtCatNighbourhood, txtCatAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCatName = itemView.findViewById(R.id.txtCatName);
            txtCatNighbourhood = itemView.findViewById(R.id.txtCatNeighbourhood);
            txtCatAge = itemView.findViewById(R.id.txtCatAge);
        }
    }
}
