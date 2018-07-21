package com.androider.weatherapp.data.network;

import com.androider.weatherapp.BuildConfig;
import com.androider.weatherapp.utility.StringConverter;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Rest client
 */
public class RestClient {

    public static ApiHelper getApiService() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setConverter(new StringConverter())
                .build();

        return restAdapter.create(ApiHelper.class);
    }

    public static ApiHelper getApiServicePojo() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        return restAdapter.create(ApiHelper.class);
    }

}

