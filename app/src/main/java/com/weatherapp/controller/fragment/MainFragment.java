package com.weatherapp.controller.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.weatherapp.R;
import com.weatherapp.api.helper.CancelableCallback;
import com.weatherapp.api.objects.GetWeatherReport;
import com.weatherapp.helper.GetLocation;
import com.weatherapp.model.ShowWeatherDetails;
import com.weatherapp.model.WeatherInfoResultObject;
import com.weatherapp.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by ankit on 30/3/15.
 */
public class MainFragment extends Fragment {

    RecyclerView mainRecyclerView;
    ArrayList<WeatherInfoResultObject> listWeatherInfo;
    CancelableCallback<GetWeatherReport> callBackGetWeather;
    EditText editTextSearchCity;
    ImageView imgSearch, imgGetLocation;
    ArrayList<ShowWeatherDetails> gridList;
    String searchedCity = "";
    GetLocation getLocation;
    Double gotLatitude, gotLongitude;
    String currentCity = "";
    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        init(rootView);

        getLocation = new GetLocation(getActivity());


        listWeatherInfo = new ArrayList<WeatherInfoResultObject>();
        gridList = new ArrayList<>();

        String hao = CommonUtils.getCurrentCity(getActivity());
        System.out.println("Current City: " + hao);

        if (!hao.equals("")) {
            CommonUtils.getWeatherDetails(getActivity(), "Indore", listWeatherInfo, gridList, mainRecyclerView);
        }

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchedCity = editTextSearchCity.getText().toString();

                if (searchedCity.equals("")) {
                    CommonUtils.showToast("Enter City Name");
                } else {
                    CommonUtils.hideKeyboard(imgSearch, getActivity());
                    CommonUtils.getWeatherDetails(getActivity(), searchedCity, listWeatherInfo, gridList, mainRecyclerView);
                }

            }
        });

        imgGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "Fetching Location...", true);
                ringProgressDialog.setCancelable(true);

                if (CommonUtils.isNetworkConnected() == true) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                Thread.sleep(25000);
                                gotLatitude = getLocation.returnLocation().getLatitude();
                                gotLongitude = getLocation.returnLocation().getLongitude();

                                getAddressFromLocation(getLocation.returnLocation().getLatitude(), getLocation.returnLocation().getLongitude(), getActivity(), new GeoCoderHandler());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ringProgressDialog.dismiss();
                        }
                    }).start();

                }
            }
        });

        mainRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mainRecyclerView.setLayoutManager(manager);

        return rootView;
    }

    private void init(View rootView) {
        mainRecyclerView = (RecyclerView) rootView.findViewById(R.id.mainRecyclerView);
        editTextSearchCity = (EditText) rootView.findViewById(R.id.editTextSearchCity);
        imgSearch = (ImageView) rootView.findViewById(R.id.imgSearch);
        imgGetLocation = (ImageView) rootView.findViewById(R.id.imgCurrentLocation);
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        public int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = space;
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }


    public String getAddressFromLocation(final double latitude, final double longitude,
                                         final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());

                try {
                    List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append(" ");
                        }
                        if (address.getLocality() != null)
                            sb.append(address.getLocality()).append(" ");

                        if (address.getPostalCode() != null)
                            sb.append(address.getPostalCode()).append(" ");

                        if (address.getCountryName() != null)
                            sb.append(address.getCountryName());

                        currentCity = sb.toString();
                        System.out.println("result: " + currentCity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return currentCity;
    }


    private class GeoCoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;

            Bundle bundle = message.getData();
            locationAddress = bundle.getString("address");

            System.out.println("data: " + locationAddress);
        }
    }

}