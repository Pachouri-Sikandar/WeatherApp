package com.weatherapp.api.helper;

import android.os.Handler;
import com.orm.SugarApp;
import com.weatherapp.R;
import com.weatherapp.util.CommonUtils;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class CustomErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError retrofitError) {
        String message="";
        if(retrofitError.isNetworkError())
        {
            message=SugarApp.getSugarContext().getString(R.string.network_failed);
        }else
        {
            Response r=retrofitError.getResponse();
            if (r != null && r.getStatus() == 408)
            {
                message=SugarApp.getSugarContext().getString(R.string.timed_out);
            }
            else if(r.getStatus()==500)
            {
                message="Sorry,Internal server error,Try later.";
            }else if(r.getStatus()==404)
            {
                message=null;
                return retrofitError;
            }
            else
            {
//                CommonUtil.showToast(retrofitError.getBody().toString());
                //COmplicated hahaha(Simply getting the first error message from JSon array list)
                try
                {
                    message=((RestError)retrofitError.getBodyAs(RestError.class)).getErrors().get(0).getMessage();
                }
                catch (Exception e)
                {
                    message="Error!Try again.";
                    e.printStackTrace();
                }
            }
        }
        if(message!=null) {
            Handler mainHandler = new Handler(SugarApp.getSugarContext().getMainLooper());
            final String finalMessage = message;
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    CommonUtils.showToast(finalMessage);
                }
            };
            mainHandler.post(myRunnable);
        }
        return retrofitError;
    }
}