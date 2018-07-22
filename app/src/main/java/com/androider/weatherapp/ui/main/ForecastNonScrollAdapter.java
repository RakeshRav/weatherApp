package com.androider.weatherapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androider.weatherapp.R;

import java.util.Iterator;

public class ForecastNonScrollAdapter extends BaseAdapter{

    private Context context;

    public ForecastNonScrollAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemView = view;

        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_forecast_tile, viewGroup, false);

            itemView.setTag(new ViewHolderForecast());
        }


        return itemView;
    }

    public class ViewHolderForecast{

    }
}
