package com.androider.weatherapp.ui.main;

import com.androider.weatherapp.di.PerActivity;
import com.androider.weatherapp.ui.base.MvpPresenter;
import com.androider.weatherapp.ui.splash.SplashMvpView;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void fetchWeatherDataFromPrefs();
}
