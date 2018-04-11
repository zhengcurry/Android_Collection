package com.curry.android.android_collection.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MyXAxisRenderer extends XAxisRenderer {

    public MyXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, xAxis, trans);
    }

    @Override
    protected void setupGridPaint() {
        super.setupGridPaint();
    }

    @Override
    public void computeAxis(float min, float max, boolean inverted) {
        super.computeAxis(min, max, inverted);
    }

    @Override
    protected void computeAxisValues(float min, float max) {
        super.computeAxisValues(min, max);
    }

    @Override
    protected void computeSize() {
        super.computeSize();
    }

    @Override
    public void renderAxisLabels(Canvas c) {
        super.renderAxisLabels(c);
    }

    @Override
    public void renderAxisLine(Canvas c) {
        super.renderAxisLine(c);
    }

    @Override
    protected void drawLabels(Canvas c, float pos, MPPointF anchor) {
        super.drawLabels(c, pos, anchor);
    }

    @Override
    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        super.drawLabel(c, formattedLabel, x, y, anchor, angleDegrees);
    }


    @Override
    public void renderGridLines(Canvas c) {
        if (!mXAxis.isDrawGridLinesEnabled() || !mXAxis.isEnabled())
            return;

        int clipRestoreCount = c.save();
        c.clipRect(getGridClippingRect());

        if (mRenderGridLinesBuffer.length != mAxis.mEntryCount * 2) {
            mRenderGridLinesBuffer = new float[mXAxis.mEntryCount * 2];
        }
        float[] positions = mRenderGridLinesBuffer;

        for (int i = 0; i < positions.length; i += 2) {
            positions[i] = mXAxis.mEntries[i / 2];
            positions[i + 1] = mXAxis.mEntries[i / 2];
        }

        mTrans.pointValuesToPixel(positions);

        setupGridPaint();

        Path gridLinePath = mRenderGridLinesPath;
        gridLinePath.reset();

        for (int i = 0; i < positions.length; i += 2) {
            drawGridLine(c, positions[i], positions[i + 1], gridLinePath);
        }

        c.restoreToCount(clipRestoreCount);
    }

    @Override
    public RectF getGridClippingRect() {
        return super.getGridClippingRect();
    }

    public void drawGridLine(Canvas c, float x, float y, Path gridLinePath) {
        gridLinePath.moveTo(x, mViewPortHandler.contentBottom());
        gridLinePath.lineTo(x, mViewPortHandler.contentTop());

        /**
         * 虚线渐变
         */
        int colorStart = Color.parseColor("#FF29DD8C");
        int colorEnd = Color.parseColor("#22252e");
        LinearGradient backGradient = new LinearGradient(0, 0, 0, c.getHeight(),
                new int[]{colorStart, colorEnd}, null, Shader.TileMode.CLAMP);
        mGridPaint.setShader(backGradient);

        // draw a path because lines don't support dashing on lower android versions
        c.drawPath(gridLinePath, mGridPaint);

        gridLinePath.reset();
    }

    @Override
    public void renderLimitLines(Canvas c) {
        super.renderLimitLines(c);
    }

    @Override
    public void renderLimitLineLine(Canvas c, LimitLine limitLine, float[] position) {
        super.renderLimitLineLine(c, limitLine, position);
    }

    @Override
    public void renderLimitLineLabel(Canvas c, LimitLine limitLine, float[] position, float yOffset) {
        super.renderLimitLineLabel(c, limitLine, position, yOffset);
    }
}
