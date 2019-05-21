package com.example.locationweatherapp.Data.Network.Service;

public interface ServiceCallBack<T> {

    void onResponse(T response);

    void onError(String message);
}