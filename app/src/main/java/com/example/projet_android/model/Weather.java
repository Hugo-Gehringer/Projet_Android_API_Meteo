package com.example.projet_android.model;

import java.util.List;

public class Weather {

    private int id;
    private String name;
    private String dayTemp;
    private String nightTemp;
    private String description;


    public Weather(String day, String dayTemp , String nightTemp) {
        this.id = id;
        this.name = day;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(String nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dayTemp='" + dayTemp + '\'' +
                ", nightTemp='" + nightTemp + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
