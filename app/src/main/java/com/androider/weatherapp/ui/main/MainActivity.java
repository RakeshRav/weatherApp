package com.androider.weatherapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androider.weatherapp.R;
import com.androider.weatherapp.data.network.model.forecastData.ForecastData;
import com.androider.weatherapp.data.network.model.forecastData.Forecastday;
import com.androider.weatherapp.ui.base.BaseActivity;
import com.androider.weatherapp.utility.CommonUtils;
import com.androider.weatherapp.utility.paralloid.views.Parallaxor;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.tvCurrentLocation)
    TextView tvCurrentLocation;

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

    }

    private void setReportInLL(ForecastData forecastData) {

        llForecast.removeAllViews();

        for (int i = 0; i < forecastData.getForecast().getForecastday().size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_forecast_tile, null, false);


            TextView tvDate = view.findViewById(R.id.tvDate);

            TextView tvMax = view.findViewById(R.id.tvMax);
            TextView tvMin = view.findViewById(R.id.tvMin);
            TextView tvWeather = view.findViewById(R.id.tvWeather);

            ImageView ivIcon = view.findViewById(R.id.ivIcon);

            Forecastday forecastday = forecastData.getForecast().getForecastday().get(i);;

            switch (i){
                case 0:
                    tvDate.setText("Today");
                    break;
                case 1:
                    tvDate.setText("Tomorrow");
                    break;

                    default:
                        tvDate.setText(String.valueOf(CommonUtils.getFormatDate(String.valueOf(forecastday.getDate()))));

            }

            Picasso.with(this).load("http:"+forecastday.getDay().getCondition().getIcon()).into(ivIcon);

            tvWeather.setText(forecastday.getDay().getCondition().getText());

            int minValue = forecastday.getDay().getMintempC().intValue();
            int maxValue = forecastday.getDay().getMaxtempC().intValue();

            tvMin.setText(Html.fromHtml(minValue+"&#xb0;"));
            tvMax.setText(Html.fromHtml(maxValue+"&#xb0;"));

            llForecast.addView(view);
        }
    }

    @Override
    public void populateDataInUI(ForecastData forecastData) {
        Log.d(TAG, "populate Data");

        if (!CommonUtils.isNullOrEmpty(forecastData.getLocation().getName())){
            tvCurrentLocation.setText(forecastData.getLocation().getName());
        }

        tvCurrentTemp.setText(String.valueOf(forecastData.getCurrent().getTempC()));

        setReportInLL(forecastData);
    }
}
