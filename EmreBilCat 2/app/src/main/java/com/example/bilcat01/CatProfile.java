package com.example.bilcat01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CatProfile extends AppCompatActivity {

    private Button buttonLocation, buttonVaccination, buttonRecentComments, buttonHome, buttonCats, buttonMap, buttonProfile;
    private TextView textViewName, textViewAge, textViewNeighbourhood;
    private ImageView imageViewCatPhoto;


    private String name, neighbourhood, age;
    private int catPhoto;


    public CatProfile() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_profile);
        initViews();
    }

    public void initViews() {
        buttonLocation = findViewById(R.id.ButtonLocation);
        buttonVaccination = findViewById(R.id.ButtonVaccination);
        buttonRecentComments = findViewById(R.id.ButtonRecentComments);
        buttonHome = findViewById(R.id.ButtonHome);
        buttonCats = findViewById(R.id.ButtonCats);
        buttonMap = findViewById(R.id.ButtonMap);
        buttonProfile = findViewById(R.id.ButtonProfile);

        textViewName = findViewById(R.id.TextViewName);
        textViewAge = findViewById(R.id.TextViewAge);
        textViewNeighbourhood = findViewById(R.id.TextViewNeighbourhood);

        imageViewCatPhoto = findViewById(R.id.ImageViewCatPhoto);

        textViewName.setText("Name: " + getName());
        textViewAge.setText("Age: " + getAge());
        textViewNeighbourhood.setText("Neighbourhood: " + getNeighbourhood());
    }

    public CatProfile(String name, String age, String neighbourhood) {
        //list.add(this);
        this.name = name;
        this.age = age;
        this.neighbourhood = neighbourhood;
        //this.vaccines = new ArrayList<String>();
        //this.comments = new ArrayList<Comment>();
        //this.recentComments = new ArrayList<Comment>();
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNeighbourhood() {
        return this.neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getCatPhoto() {
        return this.catPhoto;
    }

    public void setCatPhoto(int catPhoto) {
        this.catPhoto = catPhoto;
    }

}