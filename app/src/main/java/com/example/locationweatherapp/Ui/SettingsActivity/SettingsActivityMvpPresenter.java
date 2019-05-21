package com.example.locationweatherapp.Ui.SettingsActivity;

import android.widget.Switch;

import com.example.locationweatherapp.Ui.Base.MvpPresenter;

public interface SettingsActivityMvpPresenter<V extends SettingsActivityMvpView> extends MvpPresenter<V> {
    void getCurrentLocation();
}
