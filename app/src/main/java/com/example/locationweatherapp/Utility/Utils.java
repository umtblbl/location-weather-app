package com.example.locationweatherapp.Utility;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.locationweatherapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static final String APP_ID = "eb2ac110a3d5a767e6ffb394e5090790";

    public static void changeActivity(Activity activity, Class<?> goClass){
        Intent intent=new Intent(activity,goClass);
        activity.startActivity(intent);
    }

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd EEE MM yyy");
        String formatted = sdf.format(date);
        return formatted;

    }

    public static String convertUnixToHour(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE MM yyy");
        String formatted = sdf.format(date);
        return formatted;
    }
}
