package com.example.locationweatherapp.Ui.Base;

import android.app.Activity;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>  {

    private Activity activity;
    private V mvpView;


    public BasePresenter(Activity activity) {
        this.activity=activity;
        onAttach((V) activity); //Activity tanımlama

    }

    public V getMvpView() {
        return this.mvpView;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView=null;
    }
}
