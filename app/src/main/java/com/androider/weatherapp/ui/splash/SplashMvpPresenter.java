package com.androider.weatherapp.ui.splash;

import com.androider.weatherapp.di.PerActivity;
import com.androider.weatherapp.ui.base.MvpPresenter;

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    void makeServerCallForecast(int days);
}
