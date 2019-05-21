package com.example.locationweatherapp.Data.Network;

import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Data.Network.Service.ServiceCallBack;
import com.example.locationweatherapp.Data.Network.Service.WeatherCityNameService;

import javax.inject.Inject;

public class AppApiHelper implements ApiHelper {

    private WeatherCityNameService weatherCityNameService;

    @Inject
    public AppApiHelper(WeatherCityNameService weatherCityNameService) {
        this.weatherCityNameService = weatherCityNameService;
    }

    @Override
    public void weatherCityNameService(String cityName, ServiceCallBack<WeatherResult> callBack) {
        weatherCityNameService.weatherCityNameService(cityName, callBack);
    }

    @Override
    public void weatherCityGpsService(String lat, String lng, ServiceCallBack<WeatherResult> callBack) {
        weatherCityNameService.weatherCityGpsService(lat, lng, callBack);
    }
}
