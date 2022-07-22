package com.example.bilcat01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonGoCatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        buttonGoCatList = findViewById(R.id.ButtonGoCatList);

        buttonGoCatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CatList.class);
                Toast.makeText(MainActivity.this, "Go Cat List Clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }


}