package com.weatherapp.model;

import java.io.Serializable;

/**
 * Created by ankit on 30/3/15.
 */
public class WeatherInfoResultObject{
    String cityName, countryName, todayDate, weatherCondition, windSpeed, temperature, weatherDescription, tempMax, tempMin;


    public WeatherInfoResultObject(String cityName, String countryName, String todayDate, String weatherCondition, String windSpeed, String temperature, String weatherDescription, String tempMax, String tempMin) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.todayDate = todayDate;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
