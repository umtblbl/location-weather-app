package com.example.locationweatherapp.Ui.CitiesActivity;

import com.example.locationweatherapp.Ui.Base.MvpView;

import java.util.List;

public interface CitiesActivityMvpView extends MvpView {

    void loadCities();
    void loadToolbar();
    void getCities();
}
