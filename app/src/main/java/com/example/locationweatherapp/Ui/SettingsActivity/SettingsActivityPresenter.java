package com.example.locationweatherapp.Ui.SettingsActivity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Ui.Base.BasePresenter;
import com.example.locationweatherapp.Utility.ProvideLocation;

import javax.inject.Inject;

public class SettingsActivityPresenter<V extends SettingsActivityMvpView> extends BasePresenter<V> implements SettingsActivityMvpPresenter<V> {

    Activity activity;
    private Context context;


    @Inject
    DataManager dataManager;

    public SettingsActivityPresenter(Activity activity, Context context, DataManager dataManager) {
        super(activity);
        this.dataManager = dataManager;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void getCurrentLocation() {

    }
}