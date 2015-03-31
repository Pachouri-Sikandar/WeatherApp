package com.weatherapp.api.objects;

import com.google.gson.annotations.Expose;

/**
 * Created by ankit on 30/3/15.
 */

public class Sys {

    @Expose
    private int population;

    /**
     * @return The population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population The population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

}