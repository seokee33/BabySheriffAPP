package com.sheriffs.babysheriff.model;

public class Hospital {
    private String dutyName;
    private String dutyTime1c;
    private String dutyAddr;
    private Double Lat;
    private Double Lon;
    private String dutyTime1s;

    public Hospital() {
    }

    public Hospital(String dutyName, String dutyTime1c, String dutyAddr, Double lat, Double lon, String dutyTime1s) {
        this.dutyName = dutyName;
        this.dutyTime1c = dutyTime1c;
        this.dutyAddr = dutyAddr;
        this.Lat = lat;
        this.Lon = lon;
        this.dutyTime1s = dutyTime1s;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLon() {
        return Lon;
    }

    public void setLon(Double lon) {
        Lon = lon;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyTime1c() {
        return dutyTime1c;
    }

    public void setDutyTime1c(String dutyTime1c) {
        this.dutyTime1c = dutyTime1c;
    }

    public String getDutyAddr() {
        return dutyAddr;
    }

    public void setDutyAddr(String dutyAddr) { this.dutyAddr = dutyAddr; }

    public String getDutyTime1s() {
        return dutyTime1s;
    }

    public void setDutyTime1s(String dutyTime1s) { this.dutyTime1s = dutyTime1s; }

}
