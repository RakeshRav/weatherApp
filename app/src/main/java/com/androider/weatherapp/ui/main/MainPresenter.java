package com.androider.weatherapp.ui.main;

import android.util.Log;
import android.view.View;

import com.androider.weatherapp.BuildConfig;
import com.androider.weatherapp.data.DataManager;
import com.androider.weatherapp.data.network.RestClient;
import com.androider.weatherapp.data.network.model.forecastData.ForecastData;
import com.androider.weatherapp.ui.base.BasePresenter;
import com.androider.weatherapp.ui.splash.SplashMvpPresenter;
import com.androider.weatherapp.ui.splash.SplashMvpView;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {


    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void fetchWeatherDataFromPrefs() {
        ForecastData forecastData = getDataManager().getForecastReport();

        if (forecastData != null){
            getMvpView().populateDataInUI(forecastData);
        }else {
            Log.d(TAG,"no forecast report found");
        }
    }
}
