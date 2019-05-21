package com.example.locationweatherapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationweatherapp.Data.DataManager;
import com.example.locationweatherapp.Data.Network.Model.WeatherResult;
import com.example.locationweatherapp.Data.Network.Service.ServiceCallBack;
import com.example.locationweatherapp.R;
import com.example.locationweatherapp.Utility.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {


    Context context;
    List<String> cityList;
    WeatherResult response;

    @Inject
    DataManager dataManager;

    public WeatherAdapter(Context context, List<String> cityList, DataManager dataManager) {
        this.context = context;
        this.cityList = cityList;
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_weather, parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        dataManager.weatherCityNameService(cityList.get(position), new ServiceCallBack<WeatherResult>() {
            @Override
            public void onResponse(WeatherResult response) {
                Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                        .append(response.getWeather().get(0).getIcon())
                        .append(".png").toString()).into(holder.imgWeather);

                holder.txtCityName.setText(response.getName());

                holder.txtDescription.setText(new StringBuilder(response.getName()).append(" Weather Forecast"));

                holder.txtTemperature.setText(new StringBuilder(String.valueOf(response.getMain().getTemp()))
                        .append("°C").toString());

                holder.txtDateTime.setText(Utils.convertUnixToDate(response.getDt()));

                holder.txtPressure.setText(new StringBuilder(String.valueOf(response.getMain().getPressure()))
                        .append(" hpa").toString());

                holder.txtHumidity.setText(new StringBuilder(String.valueOf(response.getMain().getHumidity()))
                        .append("%").toString());

                holder.txtSunrise.setText(Utils.convertUnixToHour(response.getSys().getSunrise()));
                holder.txtSunset.setText(Utils.convertUnixToHour(response.getSys().getSunset()));
                holder.txtWind.setText(new StringBuilder("Speed: ").append(response.getWind().getSpeed())
                        .append(", Degree: ").append(response.getWind().getDeg()));
            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtTemperature,txtCityName,txtDescription,txtDateTime,txtWind, txtPressure,txtHumidity,txtSunrise,txtSunset;
        ImageView imgWeather;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDateTime = itemView.findViewById(R.id.txtCityDateTime2);
            txtTemperature = itemView.findViewById(R.id.txtCityTemperature2);
            txtCityName = itemView.findViewById(R.id.txtCityName2);
            txtDescription = itemView.findViewById(R.id.txtDescription2);
            txtWind = itemView.findViewById(R.id.txtWind2);
            txtPressure = itemView.findViewById(R.id.txtPressure2);
            txtSunrise = itemView.findViewById(R.id.txtSunrise2);
            txtSunset = itemView.findViewById(R.id.txtSunset2);
            txtHumidity = itemView.findViewById(R.id.txtHumidity2);
            imgWeather = itemView.findViewById(R.id.imgWeather2);

        }
    }
}
