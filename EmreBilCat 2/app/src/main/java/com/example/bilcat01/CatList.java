package com.example.bilcat01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CatList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EachCatAdapter adapter;
    private DatabaseReference database;
    private ArrayList<CatProfile> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.RecViewCatList);
        adapter = new EachCatAdapter(list, this);
        database = FirebaseDatabase.getInstance().getReference("catList");

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CatProfile catProfile = dataSnapshot.getValue(CatProfile.class);
                    list.add(catProfile);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter.setOnItemClickListener(new EachCatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CatProfile catProfile, int position) {
                Intent intent = new Intent(CatList.this, CatProfile.class);
                Toast.makeText(CatList.this, "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}