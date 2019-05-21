package com.example.locationweatherapp.Data.Pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AppPrefHelper implements PrefHelper {

    private final SharedPreferences preferences;
    private final String GPS_STATE = "GPS";
    private final String CHECKED_COUNT = "CHECKEDCOUNT";
    private final String CHECKED_CITY_LIST = "CITYLIST";
    private String prefFileName;
    private Context context;

    @Inject
    public AppPrefHelper(Context context) {
        preferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.context = context;
    }

    @Override
    public void saveGpsState(String state) {
        preferences.edit().putString(GPS_STATE, state).apply();
    }

    @Override
    public String getGpsState() {
        return preferences.getString(GPS_STATE, null);
    }

    @Override
    public void saveCheckedCityCount(int checkedCount) {
        preferences.edit().putString(CHECKED_COUNT, String.valueOf(checkedCount)).apply();
    }

    @Override
    public int getCheckedCityCount() {
        return Integer.valueOf(preferences.getString(CHECKED_COUNT, "0"));

    }

    @Override
    public void saveCheckedCityList(List<String> cityList) {
        Gson gson = new Gson();
        String value = gson.toJson(cityList);
        preferences.edit().putString(CHECKED_CITY_LIST, value).commit();
    }

    @Override
    public List<String> getCheckedCityList() {
        Gson gson = new Gson();
        String value = preferences.getString(CHECKED_CITY_LIST, null);
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> cityList = gson.fromJson(value, type);

        return cityList;
    }


}
