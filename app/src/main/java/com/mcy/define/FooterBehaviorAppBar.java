package com.mcy.define;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by Administrator on 2016/9/26 0026.
 */

public class FooterBehaviorAppBar extends CoordinatorLayout.Behavior<View> {
    public FooterBehaviorAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getY());
        Log.i("wangshu",translationY+"");
        child.setTranslationY(translationY);
        return true;
    }
}