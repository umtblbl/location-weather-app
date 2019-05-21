package com.example.locationweatherapp.Ui.HomeActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Di.DaggerApplication;
import com.example.locationweatherapp.Eventbus.LocationEvent;
import com.example.locationweatherapp.R;
import com.example.locationweatherapp.Ui.Adapters.WeatherAdapter;
import com.example.locationweatherapp.Ui.Base.BaseActivity;
import com.example.locationweatherapp.Ui.CitiesActivity.CitiesActivity;
import com.example.locationweatherapp.Ui.SettingsActivity.SettingsActivity;
import com.example.locationweatherapp.Utility.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeActivityMvpView {


    @BindView(R.id.txtCityTemperature)
    TextView txtTemperature;
    @BindView(R.id.txtCityName)
    TextView txtCityName;
    @BindView(R.id.imgWeather)
    ImageView imgWeather;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtCityDateTime)
    TextView txtDateTime;
    @BindView(R.id.txtWind)
    TextView txtWind;
    @BindView(R.id.txtPressure)
    TextView txtPressure;
    @BindView(R.id.txtHumidity)
    TextView txtHumidity;
    @BindView(R.id.txtSunrise)
    TextView txtSunrise;
    @BindView(R.id.txtSunset)
    TextView txtSunset;
    @BindView(R.id.loadingToday)
    ProgressBar progressBar;
    @BindView(R.id.cardViewHome)
    CardView cardView;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.floatingActionHome)
    FloatingActionButton floatingActionHome;
    @BindView(R.id.recyclerViewWeather)
    RecyclerView recyclerViewWeather;

    private Location location;
    List<String> cityList;

    @Inject
    DataManager dataManager;

    HomeActivityPresenter<HomeActivityMvpView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ((DaggerApplication) getApplication()).getDaggerComponent().inject(this);

        presenter = new HomeActivityPresenter(HomeActivity.this, getApplication(), dataManager);
        presenter.CreateBottomNavigatonView(bottomNavigation);


        //Seçili şehir olup olmadığının kontrolü...
        if(dataManager.getCheckedCityList() != null)
            setCityGps(dataManager.getCheckedCityList());
        else setCityGps("Izmir");

        floatingActionHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeActivity(HomeActivity.this, CitiesActivity.class);
            }
        });
    }



    public void loadBottomNavigationView(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Utils.changeActivity(this, HomeActivity.class);
                break;
            case R.id.nav_settings:
                this.finish();
                Utils.changeActivity(this, SettingsActivity.class);
                break;
        }
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(LocationEvent locationEvent) {
        location = locationEvent.getLocation();
        Toast.makeText(this, location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
        setCityGps(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));

    }

    @Override
    public void loadWeather(WeatherResult weatherResult) {

        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherResult.getWeather().get(0).getIcon())
                .append(".png").toString()).into(imgWeather);

        txtCityName.setText(weatherResult.getName());

        txtDescription.setText(new StringBuilder(weatherResult.getName()).append(" Weather Forecast"));

        txtTemperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp()))
                .append("°C").toString());

        txtDateTime.setText(Utils.convertUnixToDate(weatherResult.getDt()));

        txtPressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure()))
                .append(" hpa").toString());

        txtHumidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity()))
                .append("%").toString());

        txtSunrise.setText(Utils.convertUnixToHour(weatherResult.getSys().getSunrise()));
        txtSunset.setText(Utils.convertUnixToHour(weatherResult.getSys().getSunset()));
        txtWind.setText(new StringBuilder("Hız: ").append(weatherResult.getWind().getSpeed())
                .append(", Degree: ").append(weatherResult.getWind().getDeg()));

        cardView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCityGps(String lat, String lng) {
        recyclerViewWeather.setVisibility(View.GONE);
        presenter.getCityWeather("Null", lat, lng);

    }

    @Override
    public void setCityGps(String cityName) {
        recyclerViewWeather.setVisibility(View.GONE);
        presenter.getCityWeather("Izmir", "x", "y");

    }

    @Override
    public void setCityGps(List<String> cityList) {
        cardView.setVisibility(View.GONE);
        recyclerViewWeather.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        WeatherAdapter adapter = new WeatherAdapter(this, cityList, dataManager);
        recyclerViewWeather.setHasFixedSize(true);
        recyclerViewWeather.setAdapter(adapter);
        recyclerViewWeather.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

}
