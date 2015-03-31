package com.weatherapp.model;

/**
 * Created by ankit on 30/3/15.
 */
public class ShowWeatherDetails {

    String date, tempMax, tempMin;


    public ShowWeatherDetails(String date, String tempMax, String tempMin) {
        this.date = date;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }
}
