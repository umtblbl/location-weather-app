package com.example.locationweatherapp.Ui.SettingsActivity;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Di.DaggerApplication;
import com.example.locationweatherapp.Eventbus.LocationEvent;
import com.example.locationweatherapp.R;
import com.example.locationweatherapp.Ui.Base.BaseActivity;
import com.example.locationweatherapp.Ui.HomeActivity.HomeActivity;
import com.example.locationweatherapp.Utility.ProvideLocation;
import com.example.locationweatherapp.Utility.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements SettingsActivityMvpView {

    SettingsActivityMvpPresenter<SettingsActivityMvpView> presenter;

    @BindView(R.id.switchLocation)
    Switch switchLocation;

    @Inject
    DataManager dataManager;

    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        ((DaggerApplication) getApplication()).getDaggerComponent().inject(this);

        presenter = new SettingsActivityPresenter(SettingsActivity.this, getApplication(), dataManager);

        switchListener();



    }

    @Override
    public void switchListener() {

        String gpsState = dataManager.getGpsState();
        if(gpsState == "ON") {
            switchLocation.setChecked(true);
        }

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                   ProvideLocation provideLocation = new ProvideLocation();
                   provideLocation.getLocation(SettingsActivity.this);
                   showMessageToast("GPS ON");
                   dataManager.saveGpsState("ON");
                }
                else {
                    LocationEvent stickyEvent = EventBus.getDefault().getStickyEvent(LocationEvent.class);
                    if(stickyEvent != null)
                        EventBus.getDefault().removeStickyEvent(stickyEvent);
                    showMessageToast("GPS OFF");
                    dataManager.saveGpsState("OFF");
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        String gpsState = dataManager.getGpsState();
        if(gpsState == "ON") {
            switchLocation.setChecked(true);
        }

    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(LocationEvent locationEvent) {
        location = locationEvent.getLocation();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Utils.changeActivity(this, HomeActivity.class);
    }

}
