package com.example.locationweatherapp.Ui.Base;

public interface MvpPresenter<V extends MvpView>  {
    void onAttach(V mvpView);
    void onDetach();

}