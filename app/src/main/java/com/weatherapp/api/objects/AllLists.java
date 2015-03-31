package com.weatherapp.api.objects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * Created by ankit on 30/3/15.
 */

public class AllLists {

    @Expose
    private int dt;
    @Expose
    private Temp temp;
    @Expose
    private double pressure;
    @Expose
    private int humidity;
    @Expose
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    @Expose
    private double speed;
    @Expose
    private int deg;
    @Expose
    private int clouds;

    /**
     * @return The dt
     */
    public int getDt() {
        return dt;
    }

    /**
     * @param dt The dt
     */
    public void setDt(int dt) {
        this.dt = dt;
    }

    /**
     * @return The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * @param temp The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * @return The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * @param weather The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * @return The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public int getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(int deg) {
        this.deg = deg;
    }

    /**
     * @return The clouds
     */
    public int getClouds() {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

}