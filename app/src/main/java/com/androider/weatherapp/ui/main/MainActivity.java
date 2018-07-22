package com.androider.weatherapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.androider.weatherapp.R;
import com.androider.weatherapp.data.network.model.forecastData.ForecastData;
import com.androider.weatherapp.ui.base.BaseActivity;
import com.androider.weatherapp.utility.paralloid.views.Parallaxor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;
    @BindView(R.id.rlTop)
    RelativeLayout rlTop;
    @BindView(R.id.psvBottom)
    ScrollView psvBottom;
    @BindView(R.id.llForecast)
    LinearLayout llForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();

        mPresenter.fetchWeatherDataFromPrefs();
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void setUp() {
        if (psvBottom instanceof Parallaxor) {

            ((Parallaxor) psvBottom).parallaxViewBy(rlTop, 0.5f);
        }

        setReportInLL();
    }

    private void setReportInLL() {

        llForecast.removeAllViews();

            for (int i = 0; i < 10; i++) {
                View view = getLayoutInflater().inflate(R.layout.item_forecast_tile, null, false);
                llForecast.addView(view);
            }
    }

    @Override
    public void populateDataInUI(ForecastData forecastData) {
        Log.d(TAG, "populate Data");
    }
}
