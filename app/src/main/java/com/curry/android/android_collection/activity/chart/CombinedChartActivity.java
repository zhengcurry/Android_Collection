package com.curry.android.android_collection.activity.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.chart.DayAxisValueFormatter;
import com.curry.android.android_collection.chart.XYMarkerView;
import com.curry.android.android_collection.chart.YearXAxisFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CombinedChartActivity extends AppCompatActivity {
    //    @BindView(R.id.combined_chart)
    CombinedChart combinedChart;
    private final int itemcount = 12;
    private XAxis xAxis;
    private CombinedData data = new CombinedData();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_chart);
        ButterKnife.bind(this);
        initChart();
    }

    private void initChart() {
        combinedChart = findViewById(R.id.combined_chart);
        combinedChart.getDescription().setEnabled(false);
        combinedChart.setBackgroundColor(Color.WHITE);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDrawBarShadow(false);
        combinedChart.setHighlightFullBarEnabled(false);
        // draw bars behind lines
        combinedChart.setDrawOrder(
                new CombinedChart.DrawOrder[]{
                        CombinedChart.DrawOrder.LINE,
                        CombinedChart.DrawOrder.BAR
//                        CombinedChart.DrawOrder.BUBBLE,
//                        CombinedChart.DrawOrder.CANDLE,
//                        CombinedChart.DrawOrder.SCATTER
                });

        setXAxis();
        setYAxis();
        setLegend();
        setMarker();
        setCombinedData();
    }

    private void setXAxis() {
        xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new YearXAxisFormatter());
//        xAxis.setAxisMaximum((float) (barChartY.size()- 0.5));
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                long millis = TimeUnit.MINUTES.toMillis((long) value) + 57600000;
                return mFormat.format(new Date(millis));
            }
        });
    }

    private void setYAxis() {
        combinedChart.getAxisRight().setEnabled(false);
        /*YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)*/

        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
    }

    /**
     * 设置图例
     */
    private void setLegend() {
        Legend l = combinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
    }

    /**
     * 点击显示标志
     */
    private void setMarker() {
        XYMarkerView mv = new XYMarkerView(this, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "";
            }
        });
        mv.setChartView(combinedChart);  // For bounds control
        combinedChart.setMarker(mv);     // Set the marker to the chart
    }

    private LineData generateLineData() {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index, getRandom(15, 5)));
        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {
        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        for (int index = 0; index < itemcount; index++) {
            entries1.add(new BarEntry(index, getRandom(25, 25)));
//             //stacked
//            entries2.add(new BarEntry(0, new float[]{getRandom(13, 12), getRandom(13, 12)}));
        }
        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);


/*        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        for (int index = 0; index < itemcount; index++) {
            entries2.add(new BarEntry(0, new float[]{getRandom(13, 12), getRandom(13, 12)}));
        }
        BarDataSet set2 = new BarDataSet(entries2, "");
        set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});
        set2.setColors(new int[]{Color.rgb(61, 165, 255), Color.rgb(23, 197, 255)});
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarData d = new BarData(set1, set2);*/

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1);
        d.setBarWidth(0.9f);
        // make this BarData object grouped
//        d.groupBars(0, groupSpace, barSpace); // start at x = 0
        return d;
    }


    /**
     * 分散点图的数据
     *
     * @return
     */
    protected ScatterData generateScatterData() {
        ScatterData d = new ScatterData();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (float index = 0; index < itemcount; index += 0.5f)
            entries.add(new Entry(index + 0.25f, getRandom(10, 55)));
        ScatterDataSet set = new ScatterDataSet(entries, "Scatter DataSet");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setScatterShapeSize(7.5f);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        d.addDataSet(set);
        return d;
    }

    /**
     * 阳线图(蜡烛形)的数据
     *
     * @return
     */
    protected CandleData generateCandleData() {
        CandleData d = new CandleData();
        ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();
        for (int index = 0; index < itemcount; index += 2)
            entries.add(new CandleEntry(index + 1f, 90, 70, 85, 75f));
        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
        set.setDecreasingColor(Color.rgb(142, 150, 175));
        set.setShadowColor(Color.DKGRAY);
        set.setBarSpace(0.3f);
        set.setValueTextSize(10f);
        set.setDrawValues(false);
        d.addDataSet(set);
        return d;
    }

    /**
     * 圆点图的数据
     *
     * @return
     */
    protected BubbleData generateBubbleData() {
        BubbleData bd = new BubbleData();
        ArrayList<BubbleEntry> entries = new ArrayList<BubbleEntry>();
        for (int index = 0; index < itemcount; index++) {
            float y = getRandom(10, 105);
            float size = getRandom(100, 105);
            entries.add(new BubbleEntry(index + 0.5f, y, size));
        }
        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.WHITE);
        set.setHighlightCircleWidth(1.5f);
        set.setDrawValues(true);
        bd.addDataSet(set);
        return bd;
    }

    private float getRandom(float range, float startFrom) {
        return (float) (Math.random() * range) + startFrom;
    }


    //    @OnClick(R.id.btn_change)
    public void onClickEvent(View view) {
        setCombinedData();
    }

    private void setCombinedData() {
        removeData();
        if (flag) {
            flag = false;
            data.setData(generateLineData());

            xAxis.setAxisMinimum(0f);
            xAxis.setAxisMaximum(data.getXMax() + 0.25f);
            combinedChart.animateX(1000, Easing.EasingOption.EaseInCubic);
        } else {
            flag = true;
            data.setData(generateBarData());

            xAxis.setAxisMinimum(-0.5f);
            xAxis.setAxisMaximum(data.getXMax() + 0.5f);
            combinedChart.animateY(1000, Easing.EasingOption.EaseInCubic);
        }
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());
        combinedChart.setData(data);
        combinedChart.invalidate();
    }

    private void removeData() {
        if (combinedChart.getData() == null) {
            return;
        }
        combinedChart.getData().removeDataSet(combinedChart.getData().getDataSetByIndex(0));
        combinedChart.getData().notifyDataChanged();
        combinedChart.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
