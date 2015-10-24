package com.huahuacaocao.hhcc_common.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by zsl on 15/10/13.
 * 里面的方法用于db和px的转换
 */
public class DisplayUtils {

    public DisplayUtils() {
    }

    /**
     * dp转换为px
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPx(Context context, int dp) {
        int px = Math.round(dp * getPixelScaleFactor(context));
        return px;
    }

    /**
     * px转换为dp
     * @param context
     * @param px
     * @return
     */
    public static int pxToDp(Context context, int px) {
        int dp = Math.round(px / getPixelScaleFactor(context));
        return dp;
    }

    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
