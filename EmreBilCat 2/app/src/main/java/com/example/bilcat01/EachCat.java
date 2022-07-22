package com.example.bilcat01;

import android.widget.TextView;

import java.util.ArrayList;

public class EachCat {

    private ArrayList<CatProfile> catList;
    private TextView textViewCatName

    public static ArrayList<CatProfile> getData() {
        ArrayList<CatProfile> cats = new ArrayList<>();
        cats.add(new CatProfile("Cat30", "4", "Library"));
        cats.add(new CatProfile("Cat2", "8", "Dorm 76"));
        cats.add(new CatProfile("Cat3", "4.5", "B Building"));
        cats.add(new CatProfile("Cat4", "1", "Dorm 77"));
        cats.add(new CatProfile("Cat5", "9", "G Building"));
        return cats;
    }

}
