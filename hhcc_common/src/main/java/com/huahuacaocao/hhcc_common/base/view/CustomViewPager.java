package com.huahuacaocao.hhcc_common.base.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by zsl on 15/9/29.
 * 可以禁用滑动的ViewPager
 */
public class CustomViewPager extends ViewPager {
    //是否可以滑动
    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否可以滑动
     * @param isCanScroll
     */
    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }


    @Override
    public void scrollTo(int x, int y){
        if (isCanScroll){
            super.scrollTo(x, y);
        }
    }
}
