package com.sheriffs.babysheriff.model;

public class Temp {

    private String date;
    private float temp;

    public Temp(String date, float temp) {
        this.date = date;
        this.temp = temp;
    }

    public Temp() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}
