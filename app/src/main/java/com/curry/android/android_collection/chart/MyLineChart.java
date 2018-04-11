package com.curry.android.android_collection.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;

public class MyLineChart extends LineChart {
    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChart(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        new MyXAxisRenderer(mViewPortHandler, mXAxis, mLeftAxisTransformer).renderGridLines(canvas);
    }
}
