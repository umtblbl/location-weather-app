package com.example.locationweatherapp.Data.Network;

import com.example.locationweatherapp.Data.Network.Model.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather")
    Observable<WeatherResult> weatherCityNameService(@Query("q") String cityName,
                                                     @Query("appid") String appid,
                                                     @Query("units") String unit);
    @GET("weather")
    Observable<WeatherResult> weatherCityGpsService(@Query("lat") String lat,
                                                    @Query("lon") String lng,
                                                    @Query("appid") String appid,
                                                    @Query("units") String unit);
}
