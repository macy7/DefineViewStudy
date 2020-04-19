package com.mcy.define;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: CustomMoveView
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/19 20:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/19 20:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomMoveView extends View {

    public int lastX, lastY, screenWidth, screenHeight, statusBarHeight;
    Context context;

    public CustomMoveView(Context context) {
        super(context);
        this.context = context;
        calculate();
    }

    public CustomMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        calculate();
    }

    public CustomMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        calculate();
    }

    private void calculate() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        screenHeight = outMetrics.heightPixels;
        statusBarHeight = getStatusBarHeight();
    }


    // 获取状态栏高度
    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                int left = getLeft() + offsetX;
                int top = getTop() + offsetY;
                int right = getRight() + offsetX;
                int bottom = getBottom() + offsetY;
                Log.d("macy7", " left = " + getLeft());
                Log.d("macy7", " top = " + getTop());
                if (left < 0) {
                    left = 0;
                    right = getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = getHeight();
                }
                if (right > screenWidth) {
                    left = screenWidth - getWidth();
                    right = screenWidth;
                }
                if (bottom > (screenHeight - statusBarHeight)) {
                    top = screenHeight - statusBarHeight - getHeight();
                    bottom = screenHeight - statusBarHeight;
                }
                layout(left, top, right, bottom);
                break;
            default:
                break;
        }
        return true;
    }
}
