package com.huahuacaocao.hhcc_common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.huahuacaocao.hhcc_common.base.utils.SystemBarTintManager;
import com.huahuacaocao.hhcc_common.base.utils.TitleBarUtils;

/**
 * Created by zsl on 15/8/13.
 * Base Fragment所有的Fagment类都继承自这个类
 * 上下文（getActivity()）统一用mActivity
 * getView() 统一用mView
 */
public class BaseFragment extends Fragment {
    //透明状态栏
    protected SystemBarTintManager mTintManager;
    //是否支持透明状态栏
    protected boolean isSystemBarTint;
    //上下文
    protected FragmentActivity mActivity;
    //跟节点view
    protected View mView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = getView();
        mActivity = getActivity();
        //创建沉浸式状态栏
        TitleBarUtils.setTranslucentStatus(mActivity);
        mTintManager = new SystemBarTintManager(mActivity);
        isSystemBarTint = mTintManager.isSystemBarTint();
        //设置透明状态来为黑色字体
        mTintManager.setStatusBarDarkMode(false, mActivity);
    }


    /**
     * 设置TitleBarPadding
     *
     * @param view
     */
    protected void setTitleBarPadding(View view) {
        TitleBarUtils.setTitleBarFromPadding(mActivity, mTintManager, view);
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
        Toast.makeText(mActivity, "", Toast.LENGTH_SHORT).show();
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
