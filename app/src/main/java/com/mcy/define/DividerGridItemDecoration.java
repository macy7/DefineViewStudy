package com.mcy.define;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: DividerGridItemDecoration
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/18 10:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/18 10:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    public static final int ENABLE_OUTER_BORDER = 0x001;
    public static final int DISABLE_OUTER_BORDER = 0x002;
    private int dividerType = DISABLE_OUTER_BORDER;
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context, @DrawableRes int resId) {
        mDivider = context.getResources().getDrawable(resId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (dividerType == ENABLE_OUTER_BORDER){
            drawTop(c, parent);
            drawLeft(c, parent);
        }
        drawBottom(c, parent);
        drawRight(c, parent);

    }

    public void drawBottom(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin - mDivider.getIntrinsicWidth();
            final int right = child.getRight() + params.rightMargin;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawRight(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicWidth();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawTop(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int bottom = child.getTop() - params.topMargin;
            final int top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawLeft(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin - mDivider.getIntrinsicWidth();
            final int bottom = child.getBottom() + params.bottomMargin;
            final int right = child.getLeft() - params.leftMargin;
            final int left = right - mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int layoutPosition = parent.getChildLayoutPosition(view);
        int span = getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (dividerType == ENABLE_OUTER_BORDER){
            int top = isTop(childCount, span, layoutPosition)?mDivider.getIntrinsicWidth():0;
            int right = isRight(childCount, span, layoutPosition)?mDivider.getIntrinsicWidth():mDivider.getIntrinsicWidth();
            int left = isLeft(childCount, span, layoutPosition)?mDivider.getIntrinsicWidth():0;
            int bottom = isBottom(childCount, span, layoutPosition)?mDivider.getIntrinsicWidth():mDivider.getIntrinsicWidth();
            if (layoutManager instanceof GridLayoutManager){
                setOutRect(outRect, left,top,right,bottom,((GridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL);
            }else if (layoutManager instanceof StaggeredGridLayoutManager){
                setOutRect(outRect, left,top,right,bottom,((StaggeredGridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL);
            }
        }else if (dividerType == DISABLE_OUTER_BORDER){
            int top = isTop(childCount, span, layoutPosition)?0:mDivider.getIntrinsicWidth();
            int right = 0;
            int left = isLeft(childCount, span, layoutPosition)?0:mDivider.getIntrinsicWidth();
            int bottom = 0;
            if (layoutManager instanceof GridLayoutManager){
                setOutRect(outRect, left,top,right,bottom,((GridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL);
            }else if (layoutManager instanceof StaggeredGridLayoutManager){
                setOutRect(outRect, left,top,right,bottom,((StaggeredGridLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL);
            }
        }
    }

    /**
     * 根据显示从横放向设置rect  如果不是纵向布局  top<=>left   right<=>bottom
     * @param outRect
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param isVertical
     */
    private void setOutRect(Rect outRect, int left, int top, int right, int bottom, boolean isVertical) {
        if (isVertical){
            outRect.set(left,top,right,bottom);
        }else {//if not Vertical: top<=>left   right<=>bottom
            outRect.set(top,left,bottom,right);
        }
    }

    /**
     * 是否是在最上面的元素
     * @param childCount
     * @param span
     * @param layoutPosition
     * @return
     */
    private boolean isTop(int childCount, int span, int layoutPosition) {
        return layoutPosition/span == 0;
    }

    /**
     * 是否是靠右边的元素
     * @param childCount
     * @param span
     * @param layoutPosition
     * @return
     */
    private boolean isRight(int childCount, int span, int layoutPosition) {
        return layoutPosition + 1%span == 0;
    }

    /**
     * 是否是靠着底部的元素
     * @param childCount
     * @param span
     * @param layoutPosition
     * @return
     */
    private boolean isBottom(int childCount, int span, int layoutPosition) {
        return childCount/span == layoutPosition/span;
    }

    /**
     * 是否是靠左边的元素
     * @param childCount
     * @param span
     * @param layoutPosition
     * @return
     */
    private boolean isLeft(int childCount, int span, int layoutPosition) {
        return layoutPosition % span == 0;
    }
}
