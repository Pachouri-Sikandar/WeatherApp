package com.weatherapp.api.helper;

import com.weatherapp.api.objects.GetWeatherReport;

import java.util.Map;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by ankit on 30/3/15.
 */

public class ApiClient {

    private static WeatherForecast weatherForecast;
    public static final String ENDPOINT = "http://api.openweathermap.org";


    public static WeatherForecast getWeatherForecast() {
        if (weatherForecast == null) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setErrorHandler(new CustomErrorHandler())
                    .build();
            weatherForecast = restAdapter.create(WeatherForecast.class);
        }
        return weatherForecast;
    }

    public interface WeatherForecast {

        @GET("/data/2.5/forecast/daily")
        void getWeatherInfo(@QueryMap Map<String, String> map, CancelableCallback<GetWeatherReport> callback);

    }

}