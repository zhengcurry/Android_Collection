package com.curry.android.android_collection.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Philipp Jahoda on 14/09/15.
 */
public class YearXAxisFormatter implements IAxisValueFormatter {

    String[] mMonths = new String[]{
            "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mMonths[(int) value % mMonths.length];
    }

}
