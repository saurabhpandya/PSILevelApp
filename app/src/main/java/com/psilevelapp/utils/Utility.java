package com.psilevelapp.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.psilevelapp.PSILevelApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utility {

    public static void showToast(String message) {
        Toast.makeText(PSILevelApp.getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
    }

    public static boolean canConnect(Context context) {
        boolean canConnect = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (null != manager) {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            if (networkInfo != null
                    && networkInfo.isConnected()
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                canConnect = true;
            } else {
                Utility.showToast("No internet connection");
            }
        }
        return canConnect;
    }

    public static String returnDate(String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        return sdf.format(new Date());
    }

}
