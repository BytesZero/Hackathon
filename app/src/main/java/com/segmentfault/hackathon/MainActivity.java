package com.segmentfault.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.segmentfault.hackathon.fragment.BbsFragment;
import com.segmentfault.hackathon.fragment.MyPlantFragment;
import com.segmentfault.hackathon.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private static MainActivity mainActivity;

    //initView
    private ViewPager mViewPager;
    private ImageView tab_img_my_plant, tab_img_shop, tab_img_bbs;
    //initData
    ImageView[] tabs;
    List<Fragment> fTabs = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTitleBar();
        initView();
        initEvent();
        initData();
    }

    /**
     * 初始化TitleBar
     */
    private void initTitleBar() {

    }

    /**
     * 初始化View
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        //设置缓存页数
        mViewPager.setOffscreenPageLimit(2);
        initTab();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //viewpager的滑动事件的监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab_img_my_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTab(0);
            }
        });

        tab_img_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTab(1);
            }
        });

        tab_img_bbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTab(2);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mainActivity=this;

        fTabs.add(new MyPlantFragment());
        fTabs.add(new ShopFragment());
        fTabs.add(new BbsFragment());
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fTabs.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }


    /**
     * 初始化Tab
     */
    void initTab() {
        tab_img_my_plant = (ImageView) findViewById(R.id.tab_img_my_plant);
        tab_img_shop = (ImageView) findViewById(R.id.tab_img_shop);
        tab_img_bbs = (ImageView) findViewById(R.id.tab_img_bbs);
        tabs = new ImageView[3];
        tabs[0] = tab_img_my_plant;
        tabs[1] = tab_img_shop;
        tabs[2] = tab_img_bbs;
        tabs[0].setSelected(true);
    }

    /**
     * 设置选中
     *
     * @param select
     */
    private void selectedTab(int select) {
        for (int i = 0; i < 3; i++) {
            if (i == select) {
                tabs[i].setSelected(true);
                mViewPager.setCurrentItem(select, true);
            } else {
                tabs[i].setSelected(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    /**
     * 监听返回键
     * 如果在第一个选项卡退出，否则选中第一个选项卡
     */

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        }else{
            selectedTab(0);
        }
    }


    public static MainActivity getInstance(){
        return mainActivity;
    }

}
