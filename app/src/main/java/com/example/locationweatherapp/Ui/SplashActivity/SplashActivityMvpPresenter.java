package com.example.locationweatherapp.Ui.SplashActivity;

import com.example.locationweatherapp.Ui.Base.MvpPresenter;

public interface SplashActivityMvpPresenter<V extends SplashActivityMvpView> extends MvpPresenter<V> {
    void startApplication();
}