package com.weatherapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weatherapp.R;
import com.weatherapp.model.ShowWeatherDetails;

import java.util.ArrayList;


public class GridViewAdapter extends BaseAdapter {
    final Context cont;
    ArrayList<ShowWeatherDetails> items;
    int layoutID;

    public GridViewAdapter(Context cont, ArrayList<ShowWeatherDetails> items, int layoutID) {
        this.cont = cont;
        this.items = items;
        this.layoutID = layoutID;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.items_grid_14days, parent, false);
            holder = new ViewHolder();
            holder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
            holder.textViewTempMax = (TextView) convertView.findViewById(R.id.textViewTempMax);
            holder.textViewTempMin = (TextView) convertView.findViewById(R.id.textViewTempMin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewDate.setText(items.get(position).getDate());
        holder.textViewTempMax.setText(items.get(position).getTempMax());
        holder.textViewTempMin.setText(items.get(position).getTempMin());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewDate, textViewTempMax, textViewTempMin;
    }


}
