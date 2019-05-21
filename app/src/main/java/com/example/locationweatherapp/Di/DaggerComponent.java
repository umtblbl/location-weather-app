package com.example.locationweatherapp.Di;


import com.example.locationweatherapp.Ui.Adapters.WeatherAdapter;
import com.example.locationweatherapp.Ui.CitiesActivity.CitiesActivity;
import com.example.locationweatherapp.Ui.HomeActivity.HomeActivity;
import com.example.locationweatherapp.Ui.SettingsActivity.SettingsActivity;
import com.example.locationweatherapp.Ui.SplashActivity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {DaggerModule.class})
public interface DaggerComponent {

    void inject(SplashActivity splashActivity);
    void inject(HomeActivity homeActivity);
    void inject(SettingsActivity settingsActivity);
    void inject(CitiesActivity citiesActivity);
    void inject(WeatherAdapter weatherAdapter);
}
