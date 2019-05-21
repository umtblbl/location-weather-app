package com.example.locationweatherapp.Ui.SplashActivity;

import android.app.Activity;
import android.content.Context;

import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Ui.Base.BasePresenter;

public class SplashActivityPresenter<V extends SplashActivityMvpView> extends BasePresenter<V> implements SplashActivityMvpPresenter<V> {

    Activity activity;
    private Context context;
    DataManager dataManager;

    public SplashActivityPresenter(Activity activity, Context context,DataManager dataManager) {
        super(activity);
        this.dataManager=dataManager;
        this.context=context;
    }

    @Override
    public void startApplication() {
        getMvpView().openHomeActivity();
        getMvpView().killActivity();
    }
}
