package com.curry.android.android_collection.activity.chart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.chart.MyLineChart;
import com.curry.android.android_collection.chart.YearXAxisFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author curry
 * created at 2018/4/9 18:17
 * @desc 集成图表
 */
public class LineChartActivity extends AppCompatActivity {

//    @BindView(R.id.line_chart)
    MyLineChart lineChart;
    LineData lineData;
    LineDataSet lineSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

//        lineChart.setOnChartGestureListener(this);
        lineChart = findViewById(R.id.line_chart);
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);// no description text
        lineChart.setTouchEnabled(true);    // enable touch gestures
        lineChart.setDragEnabled(true);     // enable scaling and dragging
        lineChart.setScaleEnabled(true);    // XY都允许缩放
        lineChart.setPinchZoom(true);       // 设置x轴和y轴能否同时缩放
        lineChart.setDrawBorders(false);    // 外边框是否显示
        lineChart.setBackgroundColor(Color.parseColor("#22252e"));// 设置背景色
        lineChart.setMinOffset(20f);
        lineChart.getViewPortHandler().setMaximumScaleY(20f);   //设置Y轴最大刻度
        setXAxis();
        setYAxis();
        lineChart.animateX(1000, Easing.EasingOption.EaseInCubic);
//        lineChart.getViewPortHandler().setMaximumScaleX(2f);
//        lineChart.setScaleXEnabled(true);   // X允许缩放
//        lineChart.setScaleYEnabled(true);   // Y允许缩放
//        lineChart.setViewPortOffsets(50f, 50f, 50f, 50f);     //设置图标的偏移
//        lineChart.setOnChartValueSelectedListener(this);      //点击坐标的监听事件
//        lineChart.setVisibleXRange(20f,100f);//X可见范围
//        lineChart.setVisibleYRange(20f, 100f, YAxis.AxisDependency.LEFT);//Y可见范围
//        lineChart.animateY(3000, Easing.EasingOption.EaseInCubic);
//        lineChart.animateXY(1500, 3000);
//        lineChart.centerViewTo(1000, 100, YAxis.AxisDependency.RIGHT);  //未知？？

        // add data
        setData(12);
        setLegend();
    }

    /**
     * 设置图例
     * only possible after setting data
     */
    private void setLegend() {
        Legend legend = lineChart.getLegend();
        // modify the legend ...
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.WHITE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }

    /**
     * 绘制上下限的线
     */
    private void setLimitLine() {
        LimitLine ll1 = new LimitLine(150f, "上限上限上限上限");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setLineColor(Color.BLUE);
        ll1.setTextSize(10f);
        LimitLine ll2 = new LimitLine(-30f, "下限下限下限下限");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
//        Axis.addLimitLine(ll1);
//        Axis.addLimitLine(ll2);
        /*//设置字型
        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        ll1.setTypeface(tf);
        ll2.setTypeface(tf);*/
    }

    /**
     * 设置标志视图
     * create a custom MarkerView (extend MarkerView) and specify the layout to use for it
     */
    private void setMarkView() {
        /*MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart*/
    }

    /**
     * X轴-网格线
     */
    private void setXAxis() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(10f);             //文字大小
        xAxis.setDrawAxisLine(false);       //是否画轴线
        xAxis.setDrawGridLines(true);       //Y轴网格线
        xAxis.setTextColor(Color.WHITE);    //X轴文字
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //文字位置
        xAxis.enableGridDashedLine(10f, 10f, 0f);//绘制网格虚线1.线长，2.虚线间距，3.虚线开始坐标
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setLabelCount(24);
        xAxis.setGranularity(1f);            //间隔尺寸
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                long millis = TimeUnit.MINUTES.toMillis((long) value) + 57600000;
                return mFormat.format(new Date(millis));
            }
        });

//        xAxis.setXOffset(100f);
//        xAxis.setYOffset(50f);              //刻度与轴的距离
        xAxis.setAxisMinimum(0f);         //最小值
        xAxis.setAxisMaximum(1440f);        //最大值
//        xAxis.setCenterAxisLabels(true);  //设置标签居中--不能和固定标签同时使用
//        xAxis.setTypeface(mTfLight);/设置字型
//        xAxis.setGridDashedLine(new DashPathEffect(new float[]{10f, 10f}, 0f));//绘制网格虚线
//        xAxis.setGridColor(Color.parseColor("#FF29F59A"));//设置网格线颜色
//        xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
//        xAxis.addLimitLine(LimitLine); // add x-axis limit line
    }

    /**
     * 不设置值的范围会动态计算
     */
    private void setYAxis() {
        lineChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.setAxisMaximum(100f);  //Y轴最大值
//        leftAxis.setAxisMinimum(0f);  //Y轴最小值
        leftAxis.setDrawAxisLine(false);//是否画轴线
        leftAxis.setDrawZeroLine(false);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setGridColor(Color.parseColor("#FF29DD8C"));
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setYOffset(-6f);       //Y轴刻度向上平移
        leftAxis.setXOffset(-20f);      //Y轴刻度向右平移
//        leftAxis.setLabelCount(5);
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return (int) value + "%";
//            }
//        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * 数据源
     *
     * @param count
     */
    private void setData(int count) {
        //假数据(坐标点)
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(new Entry(i, (int) (Math.random() * 100), getResources().getDrawable(R.drawable.ic_launcher_background)));
        }

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            lineSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            lineSet.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            lineSet = new LineDataSet(values, "一个例子");

            /**
             * 折线的相关设置
             */
            lineSet.setLineWidth(2f);           //绘制线的宽度
            lineSet.setColor(Color.parseColor("#FF29DD8C")); //绘制线的颜色
//            lineSet.setCircleColor(Color.parseColor("#AF29F59A"));
            lineSet.setCircleColors(ColorTemplate.MATERIAL_COLORS);
            lineSet.setCircleRadius(6f);
            lineSet.setCircleHoleRadius(3f);        //中心圆大小
            lineSet.setDrawCircleHole(true);        //是否绘制中心圆
            lineSet.setCircleColorHole(Color.parseColor("#FF29F59A"));//中心圆颜色
            lineSet.setDrawFilled(false);           //是否填充折线与下方区域
            lineSet.setDrawIcons(false);            //设置icon在坐标点上
            lineSet.setValueTextSize(12f);          //设置坐标值的文字大小
            lineSet.setValueTextColor(Color.WHITE); //设置坐标值的颜色
            lineSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); //设置线的样式
            lineSet.setHighlightEnabled(false);
//            lineSet.setCubicIntensity(0f);                //设置线的紧度
//            lineSet.enableDashedLine(10f, 5f, 0f);        //虚线
//            lineSet.enableDashedHighlightLine(10f, 5f, 0f);   //高亮虚线
//            lineSet.setFormLineWidth(5f);
//            lineSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            lineSet.setFormSize(15.f);

            //设置折线与下方区域的drawable--setDrawFilled为true起作用
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background);
                lineSet.setFillDrawable(drawable);
            } else {
                lineSet.setFillColor(Color.BLACK);
            }

            //加载数据
            lineData = new LineData(lineSet);
            lineChart.setData(lineData);
        }
    }

    /*@Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            lineChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }*/

    //按钮点击添加数据
//    @OnClick(R.id.button)
    public void addEntry(View view) {
        if (lineSet.getEntryCount() > 24) {
            return;
        }
        Entry entry = new Entry(lineSet.getEntryCount(), (int) (Math.random() * 100));
        lineData.addEntry(entry, 0);
        lineData.notifyDataChanged();        //通知数据已经改变
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
//        lineChart.setVisibleXRangeMaximum(10);                //设置在曲线图中显示的最大数量
//        lineChart.moveViewToX(lineData.getEntryCount() - 5);  //移到某个位置
//        lineChart.moveViewTo(0, 0, YAxis.AxisDependency.LEFT);//自动调用了invalidate()
    }
}
