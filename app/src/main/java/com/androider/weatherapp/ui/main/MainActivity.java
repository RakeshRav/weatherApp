package com.androider.weatherapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.androider.weatherapp.R;
import com.androider.weatherapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.buttonForecast)
    Button buttonForecast;

    @Inject
    MainMvpPrenter<MainMvpView> prenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        prenter.onAttach(this);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openSecondActvity() {

    }

    @OnClick(R.id.buttonForecast)
    public void onViewClicked() {
        prenter.makeServerCallForecast(10);
    }
}
