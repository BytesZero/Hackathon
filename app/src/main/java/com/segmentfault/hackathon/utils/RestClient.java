package com.segmentfault.hackathon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.huahuacaocao.hhcc_common.base.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.segmentfault.hackathon.application.MyApplication;
import com.segmentfault.hackathon.config.AppConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 网络请求的工具类
 *
 * @author zsl
 */
public class RestClient {

    //AsyncHttpClient对象
    private static AsyncHttpClient client = new AsyncHttpClient();
    static {
        client.setTimeout(5000);
    }

    private static MaterialDialog materialDialog;

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        NetworkState();
        client.get(getAbsoluteUrl(url), getAbsoluteParams(params), responseHandler);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        NetworkState();
        client.post(getAbsoluteUrl(url), getAbsoluteParams(params), responseHandler);
    }

    /**
     * put 请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        NetworkState();
        client.put(getAbsoluteUrl(url), getAbsoluteParams(params), responseHandler);
    }

    /**
     * delete请求
     *
     * @param url
     * @param params
     * @param responseHandler
     */

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        NetworkState();
        client.delete(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 获得到最终的url地址
     *
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return AppConfig.SERVER + relativeUrl;
    }

    /**
     * 得到最终的url地址
     *
     * @param params
     * @return
     */
    private static RequestParams getAbsoluteParams(RequestParams params) {
        params.add("app_version", AppConfig.APP_VERSION + "");
        return params;
    }

    /**
     * 判断网络状态
     *
     * @return
     */
    public static boolean NetworkState() {
        // 判断网络是否可用
        if (!isNetworkAvailable(MyApplication.getAppContext())) {
            ToastUtils.showShortToast(MyApplication.getAppContext(), "当前网络不可用");
            cancelDialog();
            return true;
        }
        return false;
    }

    /**
     * 转码请求
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        String urlString = "";
        try {
            urlString = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return urlString;

    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 显示loadingdialog
     */
    public static void showMDDialog(Context context) {
        materialDialog = new MaterialDialog.Builder(context)
                .content("请稍等...")
                .progress(true, 0)
                .build();

        materialDialog.show();
    }

    /**
     * 取消loading dialog
     */
    public static void cancelDialog() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
    }
}
