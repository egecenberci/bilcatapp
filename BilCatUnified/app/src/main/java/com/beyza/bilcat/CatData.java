package com.beyza.bilcat;

public class CatData {
    String name, neighbourhood, age;

    public CatData() {

    }

    public CatData(String name, String neighbourhood, String age) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getAge() {
        return age;
    }

}
