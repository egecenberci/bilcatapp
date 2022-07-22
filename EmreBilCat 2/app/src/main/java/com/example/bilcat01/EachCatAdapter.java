package com.example.bilcat01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EachCatAdapter extends RecyclerView.Adapter<EachCatAdapter.EachCatHolder> {

    private ArrayList<CatProfile> catList;
    private Context context;
    private OnItemClickListener listener;



    public EachCatAdapter(ArrayList<CatProfile> catList, Context context) {
        this.catList = catList;
        this.context = context;
    }


    @NonNull
    @Override
    public EachCatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.each_cat, parent, false);
        return new EachCatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EachCatHolder holder, int position) {
        CatProfile catProfile = catList.get(position);
        holder.setData(catProfile);
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    class EachCatHolder extends  RecyclerView.ViewHolder{
        TextView catName, catNeighbourhood;
        ImageView catPhoto2;

        public EachCatHolder(@NonNull View itemView) {
            super(itemView);
            catName = (TextView)itemView.findViewById(R.id.TextViewCatName);
            catNeighbourhood = (TextView)itemView.findViewById(R.id.TextViewCatNeighbourhood);
            catPhoto2 = (ImageView)itemView.findViewById(R.id.ImageViewCatPhoto2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(catList.get(position), position);
                    }
                }
            });
        }

        public void setData(CatProfile catProfile) {
            this.getCatName().setText(catProfile.getName());
            this.getCatNeighbourhood().setText(catProfile.getNeighbourhood());
            //this.getCatPhoto2().setBackgroundResource(cat.getCatPhoto());
        }



        public TextView getCatName() {
            return catName;
        }

        public void setCatName(TextView catName) {
            this.catName = catName;
        }

        public TextView getCatNeighbourhood() {
            return catNeighbourhood;
        }

        public void setCatNeighbourhood(TextView catNeighbourhood) {
            this.catNeighbourhood = catNeighbourhood;
        }

        public ImageView getCatPhoto2() {
            return catPhoto2;
        }

        public void setCatPhoto2(ImageView catPhoto2) {
            this.catPhoto2 = catPhoto2;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CatProfile catProfile, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
