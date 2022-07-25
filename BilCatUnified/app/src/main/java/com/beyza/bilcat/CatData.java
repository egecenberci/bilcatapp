package com.beyza.bilcat;

import java.util.ArrayList;

public class CatData {
    String name, neighbourhood, age;
    VaccinationData vaccinationData;

    public CatData() {

    }

    public CatData(String name, String neighbourhood, String age, VaccinationData vaccinationData) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.age = age;
        this.vaccinationData = vaccinationData;
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

    public VaccinationData getVaccinationData() {
        return vaccinationData;
    }

    public void setVaccinationData(VaccinationData vaccinationData) {
        this.vaccinationData = vaccinationData;
    }
}
