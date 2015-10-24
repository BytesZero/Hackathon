package com.huahuacaocao.hhcc_common.base.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zsl on 15/9/14.
 * TitleBar的工具类
 */
public class TitleBarUtils {
    @TargetApi(19)
    public static void setTranslucentStatus(Activity context) {
        Window win = context.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        win.setAttributes(winParams);
    }

    /**
     * 设置TitleBarPadding
     *
     * @param view
     */

    public static void setTitleBarFromPadding(Activity context, SystemBarTintManager mTintManager, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = context.getWindow();
            /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = mTintManager.getConfig().getStatusBarHeight();
            //改变titlebar的高度
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height += statusBarHeight;
            view.setLayoutParams(lp);
            //设置paddingtop
            view.setPaddingRelative(0, statusBarHeight, 0, 0);
        }
    }
}
