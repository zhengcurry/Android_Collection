package com.curry.basic.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.heinqi.curry_base.R;

import java.util.Calendar;
import java.util.List;

import static com.curry.basic.utils.DateUtil.getThisDate;
import static com.curry.basic.utils.DateUtil.getThisMonth;
import static com.curry.basic.utils.DateUtil.getThisYear;

/**
 * 时间选择器
 * Created by curry on 2017/9/29.
 */

public class PickerUtils {
    TimePickerView mDateTimePickerView;
    OptionsPickerView mOtherPickerView;

    /**
     * 日期时间选择器配置
     */
    public void initCustomTimePicker(String titleText, final TextView textView, Activity context) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(getThisYear() + 50, getThisMonth(), getThisDate());

        initCustomTimePicker(titleText, textView, context, true, startDate,
                endDate, startDate, "yyyy年MM月dd日");
    }

    /**
     * 日期时间选择器配置-身份证日期显示
     *
     * @param titleText
     * @param textView
     * @param context
     */
    public void initCustomTimePickerID(String titleText, final TextView textView, Activity context) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(getThisYear() - 30, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(getThisYear() + 30, getThisMonth(), getThisDate());
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(getThisYear(), getThisMonth(), getThisDate());

        initCustomTimePicker(titleText, textView, context, true, startDate,
                endDate, currentDate, "yyyy-MM-dd");
    }

    /**
     * 日期时间选择器配置
     */
    public void initCustomTimePicker(String titleText, final TextView textView, Activity context,
                                     boolean dayFlag, Calendar startDate, Calendar endDate,
                                     Calendar targetDate, String formatType) {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
//        Calendar startDate = Calendar.getInstance();
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(getThisYear() + 1, getThisMonth(), getThisDate());

        //时间选择器 ，自定义布局
        mDateTimePickerView = new TimePickerBuilder(context, (date, v) -> {//选中事件回调
            mDateTimePickerView.dismiss();
            textView.setText(DateUtil.dateToString(date, formatType));
        })
                .setDate(targetDate)
                .setRangDate(startDate, endDate)//设置始末时间范围
//                .setLayoutRes(R.layout.pickerview_layout, v -> {
//                    //TODO
//                })
                .setType(new boolean[]{true, true, dayFlag, false, false, false})
                .setLabel("年", "月", "日", "点", "分", "秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setOutSideCancelable(false)
                .setSubmitText("确认")    //确定按钮文字
                .setCancelText("取消")    //取消按钮文字
                .setTitleText(titleText)//标题
                .setSubCalSize(14)      //确定和取消文字大小
                .setTitleSize(16)       //标题文字大小
                .setDividerColor(ContextCompat.getColor(context, R.color.color_E6E6E6))
                .setTitleColor(ContextCompat.getColor(context, R.color.color_333333))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_38B59B))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_999999))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)   //标题背景颜色 Night mode
                .setBgColor(Color.WHITE)        //滚轮背景颜色 Night mode
                .setContentTextSize(16)         //滚轮文字大小
                .isCenterLabel(false)           //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideCancelable(false)    //点击外部dismiss default true
                .isDialog(false)                //是否显示为对话框样式
                .build();
//                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
//                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                .gravity(Gravity.RIGHT)// default is center
        mDateTimePickerView.show();
    }

    /**
     * 不联动的多级选项
     */
    public void initNoLinkOptionsPicker(String titleText, int viewId, Activity context,
                                        List firstList, List secondList, List thirdList, boolean isLinkage,
                                        OptionsSelectListener optionsSelect) {
        mOtherPickerView = new OptionsPickerBuilder(context, (options1, options2, options3, v)
                -> optionsSelect.optionsSelect(options1, options2, options3, viewId))
                .setSubmitText("确认")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(titleText)//标题
                .setSubCalSize(14)//确定和取消文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleColor(ContextCompat.getColor(context, R.color.color_333333))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.color_38B59B))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.color_999999))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)   //标题背景颜色 Night mode
                .setBgColor(Color.WHITE)        //滚轮背景颜色 Night mode
                .setContentTextSize(18)         //滚轮文字大小
//                .setLabels("", "", "")        //设置选择的三级单位
                .isCenterLabel(false)           //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)    //点击外部dismiss default true
                .isDialog(false)                 //是否显示为对话框样式
                .isRestoreItem(false)           //切换时是否还原，设置默认选中第一项。
                .setLineSpacingMultiplier(1.4f)//设置两横线之间的间隔倍数
                .build();
        if (isLinkage) {//联动
            mOtherPickerView.setPicker(firstList, secondList, thirdList);
        } else {
            mOtherPickerView.setNPicker(firstList, secondList, thirdList);
        }
        mOtherPickerView.show();
    }

//    private ArrayList<ProvinceCityBean> firstLevelList = new ArrayList<>();
//    private ArrayList<ArrayList<String>> secondLevelList = new ArrayList<>();
//    private ArrayList<ArrayList<ArrayList<ProvinceCityBean.ChildrenBeanXX.ChildrenBeanX>>> thirdLevelList = new ArrayList<>();

    /**
     * 获取数据
     * thirdLevelList.get(options1).get(options2).get(options3).getValue()
     *
     * @return
     */

    /*public static void parseData(Object result, ArrayList<ProvinceCityBean> firstLevelList,
                                 ArrayList<ArrayList<String>> secondLevelList,
                                 ArrayList<ArrayList<ArrayList<ProvinceCityBean.ChildrenBeanXX.ChildrenBeanX>>> thirdLevelList) {
        if (result != null) {
            firstLevelList.addAll(
                    JsonUtil.fromJson(result.toString(),
                            new TypeToken<ArrayList<ProvinceCityBean>>() {
                            }));
        }
        for (ProvinceCityBean allBean : firstLevelList) {
            ArrayList<String> secondList = new ArrayList<>();
            ArrayList<ArrayList<ProvinceCityBean.ChildrenBeanXX.ChildrenBeanX>> thirdList = new ArrayList<>();

            for (ProvinceCityBean.ChildrenBeanXX childrenBeanXX : allBean.getChildren()) {
                secondList.add(childrenBeanXX.getName()); //二级

                thirdList.add(childrenBeanXX.getChildren());
            }
            secondLevelList.add(secondList);
            thirdLevelList.add(thirdList);
        }
    }*/

    public interface OptionsSelectListener {
        void optionsSelect(int options1, int options2, int options3, int viewId);
    }
}
