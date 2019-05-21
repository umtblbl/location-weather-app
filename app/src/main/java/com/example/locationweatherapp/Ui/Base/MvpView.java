package com.example.locationweatherapp.Ui.Base;

public interface MvpView {

    void showMessageToast(String text);
    void showError(String text);
    void killActivity();
}
