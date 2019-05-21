package com.example.locationweatherapp.Data;

import com.example.locationweatherapp.Data.Network.ApiHelper;
import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Data.Network.Service.ServiceCallBack;
import com.example.locationweatherapp.Data.Pref.PrefHelper;

import java.util.List;

import javax.inject.Inject;

public class AppDataManager implements DataManager {

    ApiHelper apiHelper;
    PrefHelper prefHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper, PrefHelper prefHelper) {
        this.apiHelper = apiHelper;
        this.prefHelper = prefHelper;
    }

    @Override
    public void weatherCityNameService(String cityName, ServiceCallBack<WeatherResult> callBack) {
        apiHelper.weatherCityNameService(cityName, callBack);
    }

    @Override
    public void weatherCityGpsService(String lat, String lng, ServiceCallBack<WeatherResult> callBack) {
        apiHelper.weatherCityGpsService(lat, lng, callBack);
    }


    @Override
    public void saveGpsState(String state) {
        prefHelper.saveGpsState(state);
    }

    @Override
    public String getGpsState() {
        return prefHelper.getGpsState();
    }

    @Override
    public void saveCheckedCityCount(int checkedCount) {
        prefHelper.saveCheckedCityCount(checkedCount);
    }

    @Override
    public int getCheckedCityCount() {
        return prefHelper.getCheckedCityCount();
    }

    @Override
    public void saveCheckedCityList(List<String> cityList) {
        prefHelper.saveCheckedCityList(cityList);
    }

    @Override
    public List<String> getCheckedCityList() {
        return prefHelper.getCheckedCityList();
    }

}
