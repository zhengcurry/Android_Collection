package com.curry.android.android_collection.activity.chart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.chart.DayAxisValueFormatter;
import com.curry.android.android_collection.chart.MyLineChart;
import com.curry.android.android_collection.chart.XYMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
//    @BindView(R.id.bar_chart)
    BarChart barChart;
    BarData barData;
    BarDataSet barSet;
    IAxisValueFormatter xAxisFormatter;
    protected RectF mOnValueSelectedRectF = new RectF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        ButterKnife.bind(this);
        initBarChart();
    }

    private void initBarChart() {
        barChart = findViewById(R.id.bar_chart);
        barChart.setOnChartValueSelectedListener(this);   //点击选中条目监听事件
        barChart.setPinchZoom(true);         //是否同时缩放
        barChart.setDrawBarShadow(false);     //每一个柱的背景
        barChart.setDrawValueAboveBar(true);  //在柱状图上显示值
        barChart.setMaxVisibleValueCount(60); //显示的最大数量
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setBackgroundColor(Color.parseColor("#22252e"));// 设置背景色
        barChart.animateY(1000, Easing.EasingOption.EaseInCubic);

        setXAxis();
        setYAxis();
        setLegend();
        setMarker();

        setData(12, 50);
    }

    private void setXAxis() {
        xAxisFormatter = new DayAxisValueFormatter(barChart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);           //间隔
        xAxis.setLabelCount(7);
        xAxis.setTextSize(10f);             //文字大小
        xAxis.setDrawAxisLine(false);       //是否画轴线
        xAxis.setDrawGridLines(true);       //Y轴网格线
        xAxis.setTextColor(Color.WHITE);    //X轴文字
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //文字位置
        xAxis.enableGridDashedLine(10f, 10f, 0f);//绘制网格虚线1.线长，2.虚线间距，3.虚线开始坐标
        xAxis.setValueFormatter(xAxisFormatter);
    }

    private void setYAxis() {
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "";
            }
        });
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setGridColor(Color.parseColor("#FF29DD8C"));
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        barChart.getAxisRight().setEnabled(false);//右侧Y轴不显示
        /*//右侧Y轴
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"";
            }
        });
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)*/
    }

    /**
     * 设置图例
     */
    private void setLegend() {
        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
//         l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
//         "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
    }

    /**
     * 点击显示标志
     */
    private void setMarker() {
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(barChart);  // For bounds control
        barChart.setMarker(mv);     // Set the marker to the chart
    }

    private void setData(int count, float range) {
        float start = 1f;
        //假数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.ic_launcher_background)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }

        BarDataSet barDataSet;
        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            barDataSet = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            barDataSet.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            barDataSet = new BarDataSet(yVals1, "The year 2017");
            barDataSet.setDrawIcons(false);
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextSize(12f);          //设置坐标值的文字大小
            barDataSet.setValueTextColor(Color.WHITE); //设置坐标值的颜色

            //添加多组数据
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet);

            BarData data = new BarData(barDataSet);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            barChart.setData(data);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        RectF bounds = mOnValueSelectedRectF;
        barChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = barChart.getPosition(e, YAxis.AxisDependency.LEFT);
        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());
        Log.i("x-index",
                "low: " + barChart.getLowestVisibleX() + ", high: "
                        + barChart.getHighestVisibleX());
        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {
    }
}
