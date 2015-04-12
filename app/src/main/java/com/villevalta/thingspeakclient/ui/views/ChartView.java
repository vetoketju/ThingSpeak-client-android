package com.villevalta.thingspeakclient.ui.views;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.villevalta.thingspeakclient.R;

/**
 * Created by ville on 11.04.2015.
 */
public class ChartView extends LinearLayout implements Toolbar.OnMenuItemClickListener {

    Toolbar mToolbar;
    LineChart mLineChart;

    public ChartView(Context context) {
        super(context);
        init();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_chart, this);

        mToolbar = (Toolbar) findViewById(R.id.chart_toolbar);
        mLineChart = (LineChart) findViewById(R.id.chart);

        mToolbar.inflateMenu(R.menu.chart);
        mToolbar.setOnMenuItemClickListener(this);
    }

    public void setTitle(String title){
        if(mToolbar != null) mToolbar.setTitle(title);
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    public void setData(LineData data) {
        mLineChart.setData(data);
        mLineChart.invalidate();
    }
}
