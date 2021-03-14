package com.example.projet_android.model;

public class Weather {

    private int id;
    private String day;
    private String MaxTemp;
    private String MinTemp;
    private String description;
    private String icon;


    public Weather(String day, String dayTemp , String nightTemp, String description , String icon) {
        this.id = id;
        this.day = day;
        this.MaxTemp = dayTemp;
        this.MinTemp = nightTemp;
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

    public String getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.MaxTemp = maxTemp;
    }

    public String getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(String minTemp) {
        this.MinTemp = minTemp;
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
                ", dayTemp='" + MaxTemp + '\'' +
                ", nightTemp='" + MinTemp + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
