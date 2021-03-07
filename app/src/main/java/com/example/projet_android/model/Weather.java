package com.example.projet_android.model;

public class Weather {

    private int id;
    private String day;
    private String dayTemp;
    private String nightTemp;
    private String description;
    private String icon;


    public Weather(String day, String dayTemp , String nightTemp, String description , String icon) {
        this.id = id;
        this.day = day;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
        this.description = description;
        this.icon = "https://openweathermap.org/img/wn/"+icon+"@2x.png";

    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", dayTemp='" + dayTemp + '\'' +
                ", nightTemp='" + nightTemp + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
