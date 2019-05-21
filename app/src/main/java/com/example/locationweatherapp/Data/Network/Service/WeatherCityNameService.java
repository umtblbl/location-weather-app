package com.example.locationweatherapp.Data.Network.Service;

import com.example.locationweatherapp.Data.Network.Model.WeatherResult;

public interface WeatherCityNameService {

    void weatherCityNameService(String cityName, ServiceCallBack<WeatherResult> callBack);
    void weatherCityGpsService(String lat, String lng, ServiceCallBack<WeatherResult> callBack);
}
