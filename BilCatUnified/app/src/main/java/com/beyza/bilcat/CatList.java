package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CatList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;

    public static int currentCat = -1;
    public static boolean mapClicked = false;
    public static boolean pingClicked = false;

    MyAdapter myAdapter;
    public static ArrayList<CatData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);


        recyclerView = findViewById(R.id.RecViewCatList);
        database = FirebaseDatabase.getInstance().getReference("cats");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //System.out.println(list.get(position).getName() + position + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onVaccinationClick(int position) {
                VaccinationData vaccinationData = list.get(position).getVaccinationData();
            }

            @Override
            public void onRecentCommentsClick(int position) {
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            CatData catData = dataSnapshot.getValue(CatData.class);
                            System.out.println(catData.getAge());
                        }
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onMapClick(int position) {
                mapClicked = true;
                pingClicked = false;
                currentCat = position;
            }

            @Override
            public void onPingClick(int position) {
                pingClicked = true;
                mapClicked = false;
                currentCat = position;
            }


        });



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CatData catData = dataSnapshot.getValue(CatData.class);
                    VaccinationData vaccinationData = dataSnapshot.child("vaccination").getValue(VaccinationData.class);
                    VaccinationData vacData = new VaccinationData(vaccinationData.getVac1(),
                            vaccinationData.getVac2(),
                            vaccinationData.getVac3(),
                            vaccinationData.getVac4(),
                            vaccinationData.getVac5(),
                            vaccinationData.getVac6());
                    list.add(new CatData(catData.getName(), catData.getNeighbourhood(), catData.getAge(), vacData));
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}