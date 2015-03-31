package com.weatherapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.orm.SugarApp;
import com.weatherapp.adapter.CardViewAdapter;
import com.weatherapp.api.helper.ApiClient;
import com.weatherapp.api.helper.CancelableCallback;
import com.weatherapp.api.objects.GetWeatherReport;
import com.weatherapp.model.ShowWeatherDetails;
import com.weatherapp.model.WeatherInfoResultObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ankit on 30/3/15.
 */
public class CommonUtils {

    public static void showToast(String message) {
        Toast.makeText(SugarApp.getSugarContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) SugarApp.getSugarContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            showToast("Please check your internet connection");
            return false;
        } else
            return true;
    }

    public static CancelableCallback<GetWeatherReport> callBackGetWeather;


    public static ArrayList<WeatherInfoResultObject> getWeatherDetails(final Activity activity, final String cityName, final ArrayList<WeatherInfoResultObject> listWeatherInfo, final ArrayList<ShowWeatherDetails> gridList, final RecyclerView mainRecyclerView) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final ProgressDialog ringProgressDialog = ProgressDialog.show(activity, "Fetching ...", "Weather Details...", true);
        ringProgressDialog.setCancelable(true);

        if (isNetworkConnected() == true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        HashMap<String, String> hashMap = new HashMap<String, String>();

                        hashMap.put("q", cityName);
                        hashMap.put("cnt", String.valueOf(14));
                        hashMap.put("APPID", "eeba2a675215447b4eca3e47ce5d601e");

                        ApiClient.getWeatherForecast().getWeatherInfo(hashMap, callBackGetWeather = new CancelableCallback<GetWeatherReport>(new Callback<GetWeatherReport>() {

                            @Override
                            public void success(GetWeatherReport getWeatherReport, Response response) {
                                System.out.println("Success...");

                                DateFormat df = DateFormat.getDateTimeInstance();


                                long unixSeconds = getWeatherReport.getList().get(0).getDt();
                                Date date = new Date(unixSeconds * 1000L);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom

                                String cityName = getWeatherReport.getCity().getName();
                                String countryName = getWeatherReport.getCity().getCountry();
                                String currentDate = sdf.format(date);
                                String weatherCondition = getWeatherReport.getList().get(0).getWeather().get(0).getMain();
                                String windSpeed = String.valueOf(getWeatherReport.getList().get(0).getPressure());

                                String actualTemp = String.valueOf(getWeatherReport.getList().get(0).getDeg());
                                String weatherDescription = getWeatherReport.getList().get(0).getWeather().get(0).getDescription();
                                String tempMax = String.valueOf(getWeatherReport.getList().get(0).getTemp().getMax());
                                String tempMin = String.valueOf(getWeatherReport.getList().get(0).getTemp().getMin());

                                WeatherInfoResultObject weatherInfoResultObject = new WeatherInfoResultObject(cityName, countryName, currentDate, weatherCondition, windSpeed, actualTemp, weatherDescription, tempMax, tempMin);
                                listWeatherInfo.add(weatherInfoResultObject);

                                for (int loopDays = 0; loopDays <= 13; loopDays++) {
                                    Date dateed = new Date(getWeatherReport.getList().get(loopDays).getDt() * 1000L);
                                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");
                                    sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

                                    String datee = sdf1.format(dateed);
                                    String maxi = String.valueOf(getWeatherReport.getList().get(loopDays).getTemp().getMax());
                                    String mini = String.valueOf(getWeatherReport.getList().get(loopDays).getTemp().getMin());
                                    ShowWeatherDetails showWeatherDetails = new ShowWeatherDetails(datee, maxi, mini);
                                    gridList.add(showWeatherDetails);
                                }


                                if (listWeatherInfo != null && listWeatherInfo.size() > 0) {
                                    mainRecyclerView.setVisibility(View.VISIBLE);
                                    mainRecyclerView.setAdapter(new CardViewAdapter(activity, listWeatherInfo, gridList));
                                    System.out.println("Size: " + listWeatherInfo.size());
                                } else {
                                    mainRecyclerView.setVisibility(View.GONE);
                                    System.out.println("Size else: " + listWeatherInfo.size());
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                System.out.println("Failure...");

                            }
                        }));
                        Thread.sleep(3000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();

        }
        return listWeatherInfo;
    }


    public static LocationManager locationManager = null;
    public static LocationListener locationListener = null;
    public static boolean flag = false;
    public static String currentCityName = "";

    public static String getCurrentCity(Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final ProgressDialog ringProgressDialog = ProgressDialog.show(activity, "Please wait ...", "Fetching Location...", true);
        ringProgressDialog.setCancelable(true);

        if (isNetworkConnected() == true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        locationManager = (LocationManager) SugarApp.getSugarContext().getSystemService(Context.LOCATION_SERVICE);
                        flag = displayGpsStatus();
                        if (flag) {

                            locationListener = new MyLocationListener();

                            locationManager.requestLocationUpdates(LocationManager
                                    .GPS_PROVIDER, 20000, 10, locationListener);

                        } else {
                            CommonUtils.showToast("Check Your GPS");
                        }

                        System.out.println("Current City: " + currentCityName);

                        Thread.sleep(25000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();

        }

        return currentCityName;
    }


    private static Boolean displayGpsStatus() {
        ContentResolver contentResolver = SugarApp.getSugarContext().getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    static class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {

            String cityName = null;
            Geocoder gcd = new Geocoder(SugarApp.getSugarContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }

            currentCityName = cityName;
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}
