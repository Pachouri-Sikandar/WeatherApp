package com.weatherapp.api.objects;

import com.google.gson.annotations.Expose;

/**
 * Created by ankit on 30/3/15.
 */

public class Coord {

    @Expose
    private double lon;
    @Expose
    private double lat;

    /**
     * @return The lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

}