package com.androider.weatherapp.ui.main;

import com.androider.weatherapp.data.network.model.forecastData.ForecastData;
import com.androider.weatherapp.ui.base.MvpView;

public interface MainMvpView extends MvpView{

    void populateDataInUI(ForecastData forecastData);
}
