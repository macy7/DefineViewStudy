package com.mcy.define.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * @ProjectName: GpsHook
 * @Package: com.markypq.gpshook
 * @ClassName: SpiderVIew
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2019/9/2 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/9/2 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SpiderView extends View {
    Paint mPaint;
    private int TOTAL_COUNT = 6;
    private int radius = 360;
    private int perRadius;
    int centerX, centerY;
    private Context mContext;
    private Path mPath;
    private float[] mData = new float[]{1, 2, 3, 4, 5, 6};

    public SpiderView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        centerX = getScreenWidth() / 2;
        centerY = 720;
        perRadius = radius / 6;
        mPath = new Path();
    }

    private int getScreenWidth() {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (manager != null)
            manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSpiderView(canvas);
        drawDataPath(canvas);
    }

    private void drawSpiderView(Canvas canvas) {
        for (int i = 1; i <= TOTAL_COUNT; i++) {
            mPath.reset();
            for (int j = 0; j < TOTAL_COUNT; j++) {
                float pointX = (float) (centerX + perRadius * i * Math.cos(Math.PI / 3 * j));
                float pointY = (float) (centerY + perRadius * i * Math.sin(Math.PI / 3 * j));
                if (j == 0) {
                    mPath.moveTo(pointX, pointY);
                } else {
                    mPath.lineTo(pointX, pointY);
                }
                if (i == TOTAL_COUNT) {
                    canvas.drawLine(centerX, centerY, pointX, pointY, mPaint);
                    pointX = (float) (centerX + (perRadius * i + 60) * Math.cos(Math.PI / 3 * j));
                    pointY = (float) (centerY + (perRadius * i + 60) * Math.sin(Math.PI / 3 * j));
                    mPaint.setTextSize(30);
                    mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawText("a", pointX, pointY, mPaint);

                }
            }
            mPath.close();
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawDataPath(Canvas canvas) {
        mPath.reset();
        for (int i = 0; i < mData.length; i++) {
            float dataX = (float) (centerX + mData[i] / TOTAL_COUNT * radius * Math.cos(Math.PI / 3 * i));
            float dataY = (float) (centerY + mData[i] / TOTAL_COUNT * radius * Math.sin(Math.PI / 3 * i));
            if (i == 0) {
                mPath.moveTo(dataX, dataY);
            } else {
                mPath.lineTo(dataX, dataY);
            }
        }
        mPath.close();
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(128);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(mPath, mPaint);
    }

}
