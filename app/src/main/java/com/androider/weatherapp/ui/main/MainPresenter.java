package com.androider.weatherapp.ui.main;

import android.util.Log;

import com.androider.weatherapp.BuildConfig;
import com.androider.weatherapp.data.DataManager;
import com.androider.weatherapp.data.network.model.forecastData.ForecastData;
import com.androider.weatherapp.data.network.RestClient;
import com.androider.weatherapp.ui.base.BasePresenter;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPrenter<V>{


    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void makeServerCallForecast(int days) {
        getMvpView().showLoading();
        Log.d(TAG,"Making Server call for forevast");

        RestClient.getApiServicePojo()
                .getWeatherForecastWithDays(BuildConfig.API_KEY,
                "Bangalore",
                "7", new Callback<ForecastData>() {
                    @Override
                    public void success(ForecastData s, Response response) {
                        Log.d(TAG,"Success : "+new Gson().toJson(s));
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(TAG,"failure : "+error.toString());
                        getMvpView().hideLoading();
                    }
                });
    }
}