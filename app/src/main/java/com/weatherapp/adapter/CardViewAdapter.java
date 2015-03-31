package com.weatherapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.model.ShowWeatherDetails;
import com.weatherapp.model.WeatherInfoResultObject;

import java.util.ArrayList;


/**
 * Created by ankit on 30/3/15.
 */
public class CardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    ArrayList<WeatherInfoResultObject> reviews_list;
    ArrayList<ShowWeatherDetails> gridList;
    Context context;
    LayoutInflater layoutInflater;
    View v;

    public CardViewAdapter(Context context, ArrayList<WeatherInfoResultObject> reviews_list, ArrayList<ShowWeatherDetails> gridList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.reviews_list = reviews_list;
        this.gridList = gridList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_ITEM) {
            v = layoutInflater.inflate(R.layout.cardview_cities_weather, null, false);
            return new ReviewsViewHolder(v);
        }
        throw new RuntimeException("No Match Found");
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof ReviewsViewHolder) {
            WeatherInfoResultObject resultObject = getItem(i);
            ReviewsViewHolder.textViewCityName.setText(resultObject.getCityName());
            ReviewsViewHolder.textViewCountryName.setText(resultObject.getCountryName());
            ReviewsViewHolder.textViewDate.setText(resultObject.getTodayDate());
            ReviewsViewHolder.textViewWeatherCondition.setText(resultObject.getWeatherCondition());
            ReviewsViewHolder.textViewWindSpeed.setText("Pressure:" + resultObject.getWindSpeed());
            ReviewsViewHolder.textViewTodaysTemp.setText(resultObject.getTemperature());
            ReviewsViewHolder.textViewDescription.setText(resultObject.getWeatherDescription());
            ReviewsViewHolder.textViewTempMax.setText(resultObject.getTempMax());
            ReviewsViewHolder.textViewTempMin.setText(resultObject.getTempMin());

            GridViewAdapter gridViewAdapter = new GridViewAdapter(context, gridList, R.layout.items_grid_14days);

            ReviewsViewHolder.gridView.setAdapter(gridViewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return reviews_list.size();
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }

    private WeatherInfoResultObject getItem(int position) {
        return reviews_list.get(position);
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public static class ReviewsViewHolder extends RecyclerView.ViewHolder {
        protected static TextView textViewCityName, textViewCountryName, textViewDate, textViewWeatherCondition, textViewWindSpeed, textViewTodaysTemp, textViewDescription, textViewTempMax, textViewTempMin;
        protected static ImageView imageViewWeatherImage;
        protected static GridView gridView;

        public ReviewsViewHolder(View v) {
            super(v);
            textViewCityName = (TextView) v.findViewById(R.id.textViewCityName);
            textViewCountryName = (TextView) v.findViewById(R.id.textViewCountryName);
            textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            textViewWeatherCondition = (TextView) v.findViewById(R.id.textViewWeatherCondition);
            textViewWindSpeed = (TextView) v.findViewById(R.id.textViewWindSpeed);
            imageViewWeatherImage = (ImageView) v.findViewById(R.id.imgWeather);
            textViewTodaysTemp = (TextView) v.findViewById(R.id.textViewTemperature);
            textViewDescription = (TextView) v.findViewById(R.id.textViewWeatherDescription);
            textViewTempMax = (TextView) v.findViewById(R.id.textViewTempMax);
            textViewTempMin = (TextView) v.findViewById(R.id.textViewTempMin);

            gridView = (GridView) v.findViewById(R.id.gridView);

        }
    }
}
