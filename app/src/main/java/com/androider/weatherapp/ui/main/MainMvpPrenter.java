package com.androider.weatherapp.ui.main;

import com.androider.weatherapp.di.PerActivity;
import com.androider.weatherapp.ui.base.MvpPresenter;
import com.androider.weatherapp.ui.base.MvpView;

@PerActivity
public interface MainMvpPrenter<V extends MainMvpView> extends MvpPresenter<V> {

    void makeServerCallForecast(int days);
}
