package com.example.locationweatherapp.Ui.HomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Data.Network.Service.ServiceCallBack;
import com.example.locationweatherapp.Ui.Base.BasePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import javax.inject.Inject;

public class HomeActivityPresenter<V extends HomeActivityMvpView> extends BasePresenter<V> implements HomeActivityMvpPresenter<V> {

    Activity activity;
    private Context context;

    @Inject
    DataManager dataManager;

    public HomeActivityPresenter(Activity activity,Context context, DataManager dataManager) {
        super(activity);
        this.dataManager = dataManager;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void CreateBottomNavigatonView(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                getMvpView().loadBottomNavigationView(menuItem);
                return true;
            }
        });
    }

    @Override
    public void getCityWeather(String cityName, String lat, String lng) {

        if(cityName != "Null") {
            dataManager.weatherCityNameService(cityName, new ServiceCallBack<WeatherResult>() {
                @Override
                public void onResponse(WeatherResult response) {

                    getMvpView().loadWeather(response);

                }

                @Override
                public void onError(String message) {
                    getMvpView().showError(message);
                }
            });
        }
        else {
            dataManager.weatherCityGpsService(lat, lng, new ServiceCallBack<WeatherResult>() {
                @Override
                public void onResponse(WeatherResult response) {

                    getMvpView().loadWeather(response);

                }

                @Override
                public void onError(String message) {
                    getMvpView().showError(message);
                }
            });
        }

    }

}
