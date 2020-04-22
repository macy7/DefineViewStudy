package com.mcy.define;

import android.content.Context;
import android.text.PrecomputedText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.security.MessageDigest;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: HorizontalScrollView
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/22 17:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/22 17:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HorizontalScrollView extends ViewGroup {
    int interceptX, interceptY, lastX, lastY;
    Scroller mScroller;
    int childIndex = 0;
    int childWidth = 0;
    VelocityTracker mVelocityTracker;

    public HorizontalScrollView(Context context) {
        super(context);
        initView(context);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context c){
        mScroller = new Scroller(c);
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST
                && heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int measuredWidth = childOne.getMeasuredWidth();
            int measuredHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(measuredWidth * getChildCount(), measuredHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int measuredWidth = childOne.getMeasuredWidth();
            setMeasuredDimension(measuredWidth * getChildCount(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int measuredHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(widthSize, measuredHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != View.GONE) {
                childWidth = childAt.getMeasuredWidth();
                childAt.layout(left, 0,
                        left + childWidth, childAt.getMeasuredHeight());
                left += childWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - interceptX;
                int deltaY = y - interceptY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        interceptX = x;
        interceptY = y;
        lastX = x;
        lastY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - childIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    if(distance > 0){
                        childIndex++;
                    }else {
                        childIndex--;
                    }
                }else {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = mVelocityTracker.getXVelocity();
                    if(Math.abs(xVelocity) > 50){
                        if(xVelocity > 0){
                            childIndex--;
                        }else {
                            childIndex++;
                        }
                    }
                }
                childIndex = childIndex < 0? 0: Math.min(childIndex, getChildCount() - 1);
                smoothScrollTo(childIndex * childWidth, 0);
                mVelocityTracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollTo(int destX, int destY){
        mScroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY());
        postInvalidate();
    }
}
