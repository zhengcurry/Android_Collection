package com.curry.android.android_collection.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author curry
 *         created at 2018/4/17 10:38
 * @desc 自定义view
 */
public class MyView extends View {
    private Paint mPaint = new Paint();
//    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAlpha(10);        //set the alpha component [0..255] of the paint's color
        mPaint.setDither(true);     //设置防抖动
        mPaint.setAntiAlias(true);  //true to set the antialias bit in the flags, false to clear it
        mPaint.setColor(Color.BLUE);
        mPaint.setARGB(100, 41, 245, 154);
        /**
         * 修改为画线模式
         * FILL 是填充模式;
         * STROKE 是画线模式（即勾边模式）;
         * FILL_AND_STROKE 是既画线又
         * 它的默认值是 FILL，填充模式。
         */
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置线条的宽度；在 STROKE 和 FILL_AND_STROKE 下,起作用
        mPaint.setStrokeWidth(10);
        //TODO 未完
        mPaint.setColorFilter(new ColorFilter());//设置颜色过滤
//        mPaint.setElegantTextHeight();
//        mPaint.setFakeBoldText();
//        mPaint.setFilterBitmap();
//        mPaint.setFlags();
//        mPaint.setFontFeatureSettings();
//        mPaint.setFontVariationSettings();

        /***绘制一个圆：（圆心x坐标、圆心y坐标、半径、风格）***/
        canvas.drawCircle(200, 200, 100, mPaint);

        /***在整个绘制区域统一涂上指定的颜色。***/
//        canvas.drawColor(Color.RED);
//        canvas.drawColor(Color.parseColor("#88880000"));
//        canvas.drawRGB(100, 200, 100);
        canvas.drawARGB(50, 100, 200, 100);

        /***绘制一个矩形：（left、top、right、bottom、风格）***/
        canvas.drawRect(100, 100, 500, 500, mPaint);

        /****绘制一个点***/
        Paint pointPaint = new Paint();
        // 点的大小
        pointPaint.setStrokeWidth(20);
        // ROUND 画出来是圆形的点，SQUARE 或 BUTT 画出来是方形的点
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(50, 50, pointPaint);
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        // 跳过两个数，即前两个 0
        // 一共绘制 8 个数（4 个点）
//        canvas.drawPoints(points, pointPaint);
        canvas.drawPoints(points, 2, 8, pointPaint);

        /***绘制一个椭圆 left, top, right, bottom ***/
        canvas.drawOval(100, 300, 500, 500, mPaint);

        /***绘制一条线 startX, startY, stopX, stopY 分别是线的起点和终点坐标 ***/
        canvas.drawLine(100, 100, 500, 500, mPaint);
        mPaint.setColor(Color.BLUE);
        float[] linePoints = {
                290, 490, 300, 515,
                250, 510, 260, 550,
                255, 525, 350, 525,
                350, 525, 340, 550,
                245, 560, 355, 560,
                320, 535, 320, 645,
                320, 645, 300, 625,
                300, 605, 280, 585
        };
//        canvas.drawLines(linePoints, 2, 8, mPaint);
        canvas.drawLines(linePoints, mPaint);

        /***绘制圆角矩形 left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径 ***/
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(100, 41, 245, 154);
        canvas.drawRoundRect(100, 600, 500, 900, 50, 50, mPaint);

        /***绘制弧形或扇形
         *  left, top, right, bottom 描述的是这个弧形所在的椭圆
         *  startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         *  sweepAngle 是弧形划过的角度；
         *  useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形
         *  ***/
        canvas.drawOval(100, 920, 500, 1100, mPaint);//椭圆
        // 绘制扇形
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(100, 920, 500, 1100, -110, 100, true, mPaint);
        // 绘制弧形
        canvas.drawArc(100, 920, 500, 1100, 20, 140, false, mPaint);
        // 绘制不封口的弧形
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(100, 920, 500, 1100, 180, 60, false, mPaint);

        /***
         * 绘制自定义图形
         */
        Paint paint = new Paint();
        paint.setARGB(50, 228, 40, 20);
        Path path = new Path(); // 初始化 Path 对象
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形），大功告成
    }
}
