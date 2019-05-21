package com.example.locationweatherapp.Di;

import android.app.Application;
import android.content.Context;

import com.example.locationweatherapp.Data.AppDataManager;
import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Data.Network.ApiHelper;
import com.example.locationweatherapp.Data.Network.AppApiHelper;
import com.example.locationweatherapp.Data.Network.Service.WeatherCityNameService;
import com.example.locationweatherapp.Data.Network.Service.WeatherCityNameServiceImp;
import com.example.locationweatherapp.Data.Pref.AppPrefHelper;
import com.example.locationweatherapp.Data.Pref.PrefHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {


    private Context context;

    public DaggerModule(Application app) {
        this.context = app;
    }

    @Provides
    Context providesContext() {
        return context;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(ApiHelper apiHelper, PrefHelper prefHelper) {
        return new AppDataManager(apiHelper, prefHelper);
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(WeatherCityNameService weatherCityNameService) {
        return new AppApiHelper(weatherCityNameService);
    }


    @Provides
    @Singleton
    WeatherCityNameService weatherCityNameService(){
        return new WeatherCityNameServiceImp();
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context){
        return new AppPrefHelper(context);
    }

}
