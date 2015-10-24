package com.segmentfault.hackathon.application;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.Bugtags;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.Logger;
import com.segmentfault.hackathon.R;
import com.segmentfault.hackathon.config.AppConfig;


/**
 * Created by zsl on 15/10/24.
 */
public class MyApplication extends Application{
    private static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        //init BugTags
        Bugtags.start("6e842653797324611e93598d56bf23b4", this, Bugtags.BTGInvocationEventBubble);
        //初始化logger
        Logger.init(AppConfig.LOG_TAG);
        /**
         * 初始化ImageLoader
         */
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.mipmap.img_no_photos)
                .showImageOnLoading(R.mipmap.img_no_photos)
                .showImageOnFail(R.mipmap.img_no_photos)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(config);
    }
    public static Context getAppContext() {
        return sInstance;
    }
}
