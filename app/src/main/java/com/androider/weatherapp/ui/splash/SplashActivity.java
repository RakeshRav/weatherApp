package com.androider.weatherapp.ui.splash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.androider.weatherapp.R;
import com.androider.weatherapp.ui.base.BaseActivity;
import com.androider.weatherapp.utility.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView {


    private static final String TAG = SplashActivity.class.getSimpleName();
    @Inject
    SplashMvpPresenter<SplashMvpView> prenter;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        prenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        tvTitle.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTitle.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in);
                tvTitle.startAnimation(animation);

                doForecaseApiDelay();
            }
        },1000);

    }

    private void doForecaseApiDelay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                makeServerCall();
            }
        },1500);
    }

    @Override
    public void makeServerCall() {
        if (isNetworkConnected()){
            prenter.makeServerCallForecast(10);
        }else {
            showErrorDialog("No Internet Connection Available", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismissErrDialog();
                    makeServerCall();
                }
            });
        }
    }

    @Override
    public void openSecondActvity() {

    }
}
