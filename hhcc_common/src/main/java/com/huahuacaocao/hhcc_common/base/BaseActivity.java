package com.huahuacaocao.hhcc_common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bugtags.library.Bugtags;
import com.huahuacaocao.hhcc_common.base.utils.SystemBarTintManager;
import com.huahuacaocao.hhcc_common.base.utils.TitleBarUtils;

/**
 * Created by zsl on 15/8/8.
 * Base Activity 所有的Activity都继承自这个类
 * 上下文统一用mActivity
 */

public class BaseActivity extends AppCompatActivity {
    //透明状态栏
    protected SystemBarTintManager mTintManager;
    //是否支持透明状态栏
    protected boolean isSystemBarTint;
    //是否未miui6及以上系统
    protected boolean isMiuiV6;
    //上下文
    protected Activity mActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        //设置沉浸式状态栏
        TitleBarUtils.setTranslucentStatus(this);
        mTintManager = new SystemBarTintManager(this);
        isMiuiV6 = mTintManager.isSystemBarTint();
        isSystemBarTint = mTintManager.ismStatusBarAvailable();
        //设置透明状态来为白色字体
        mTintManager.setStatusBarDarkMode(false, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    /**
     * 设置TitleBarPadding
     *
     * @param view
     */
    protected void setTitleBarPadding(View view) {
        TitleBarUtils.setTitleBarFromPadding(this, mTintManager, view);
    }


    /**
     * 显示长的吐司
     * @param content
     */
    protected void showLongToast(String content){
        Toast.makeText(mActivity, "" + content, Toast.LENGTH_LONG).show();
    }

    /**
     *  显示短的吐司
     * @param content
     */
    protected void showShortToast(String content){
        Toast.makeText(mActivity, "" + content, Toast.LENGTH_SHORT).show();
    }


    /**
     * startActivity
     * @param cls
     */
    protected void baseStartActivity(Class<?> cls){
        Intent intent=new Intent(mActivity,cls);
        startActivity(intent);
    }

    /**
     * startActivityForResult
     * @param cls
     * @param requestCode
     */
    protected void baseStartActivityForResult(Class<?> cls,int requestCode){
        Intent intent=new Intent(mActivity,cls);
        startActivityForResult(intent, requestCode);
    }
}

