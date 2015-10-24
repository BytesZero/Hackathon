package com.segmentfault.hackathon.utils;

import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;

/**
 * 控件的工具类
 * @author zsl
 *
 */
public class ViewUtils {


    /**
     * 设置webview
     * @param webView
     * @param url
     */
    public static void setWebView(WebView webView,String url){
        if (!TextUtils.isEmpty(url)) {
            if (webView!=null) {
                webView.loadUrl(url + "");
                //开启javascript
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setAppCacheEnabled(true);
                //控制在webview中打开所有链接
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        String data = "<div style='text-align: center;'>加载失败<br/>请下拉刷新</div>";
                        view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
                    }
                });
            }else{
                Logger.e("setWebView的 webView 不能为空");
            }
        }else{
            Logger.e("setWebView的 url 不能为空");
        }
    }
}
