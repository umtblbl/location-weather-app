package com.example.locationweatherapp.Ui.HomeActivity;

import com.example.locationweatherapp.Ui.Base.MvpPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public interface HomeActivityMvpPresenter<V extends HomeActivityMvpView> extends MvpPresenter<V> {

    void CreateBottomNavigatonView(BottomNavigationView bottomNavigationView);
    void getCityWeather(String cityName, String lan, String lng);
}
