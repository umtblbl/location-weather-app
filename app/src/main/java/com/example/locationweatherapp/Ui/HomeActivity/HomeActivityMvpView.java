package com.example.locationweatherapp.Ui.HomeActivity;


import android.view.MenuItem;

import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Ui.Base.MvpView;

import java.util.List;


public interface HomeActivityMvpView extends MvpView {

    void loadBottomNavigationView(MenuItem menuItem);
    void loadWeather(WeatherResult weatherResult);
    void setCityGps(String lat, String lng);
    void setCityGps(String cityName);
    void setCityGps(List<String> cityList);
}
