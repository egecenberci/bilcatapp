package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentList extends AppCompatActivity {

    EditText editTextComment;
    Button buttonAddComment;

    RecyclerView recyclerView;
    DatabaseReference database;
    CommentAdapter commentAdapter;
    ArrayList<CommentData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        editTextComment = findViewById(R.id.EditTextComment);
        buttonAddComment = findViewById(R.id.ButtonAddComment);

        DAOComment dao = new DAOComment();

        buttonAddComment.setOnClickListener(view ->
        {
            CommentData commentData = new CommentData(editTextComment.getText().toString(), CatList.list.get(CatList.currentCat), CatList.currentCat, MapsActivity.ping.getPosition());
            dao.add(commentData).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Comment is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });


        recyclerView = findViewById(R.id.RecViewCommentList);
        database = FirebaseDatabase.getInstance().getReference("CommentData");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, list);
        recyclerView.setAdapter(commentAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CommentData commentData = dataSnapshot.getValue(CommentData.class);
                    if (commentData.getCurrentCat() == CatList.currentCat) {
                        list.add(commentData);
                    }
                }
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}