package com.mcy.define;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

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
    private Scroller mScroller;

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
        mScroller = new Scroller(context);
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
//                layout(left, top, right, bottom);

                /*offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/

                /*ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);*/

                //使用scrollBy
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            default:
                break;
        }
        return true;
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int delta = destX - scrollX;
        int deltaY = destY - scrollY;
        //1000秒内滑向destX
        mScroller.startScroll(scrollX, scrollY, delta, deltaY, 80000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }

    }
}
