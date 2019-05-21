package com.example.locationweatherapp.Eventbus;

import com.example.locationweatherapp.Data.Network.Model.WeatherResult;

public class CityNameWeatherEvent {

    private WeatherResult weatherResult;

    public CityNameWeatherEvent(WeatherResult weatherResult) {
        this.weatherResult = weatherResult;
    }

    public WeatherResult getWeatherResult() {
        return weatherResult;
    }

    public void setWeatherResult(WeatherResult weatherResult) {
        this.weatherResult = weatherResult;
    }
}
