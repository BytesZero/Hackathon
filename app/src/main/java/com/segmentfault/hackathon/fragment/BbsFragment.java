package com.segmentfault.hackathon.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.huahuacaocao.hhcc_common.base.BaseFragment;
import com.segmentfault.hackathon.R;
import com.segmentfault.hackathon.utils.ViewUtils;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class BbsFragment extends BaseFragment {

    WebView wv_bbs;

    PtrClassicFrameLayout ptr_bbs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_bbs, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initTitlebar();
        wv_bbs= (WebView) mView.findViewById(R.id.bbs_wv_bbs);
        ViewUtils.setWebView(wv_bbs, "http://bbs.xiaomi.cn/");
        
        ptr_bbs= (PtrClassicFrameLayout) mView.findViewById(R.id.bbs_ptr_bbs);
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    public void initData() {
        //设置下拉刷新
        ptr_bbs.setLastUpdateTimeRelateObject(this);
        ptr_bbs.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                //设置更新数据
                updateData();
            }
        });
    }

    //更新数据
    private void updateData() {
        ptr_bbs.postDelayed(new Runnable() {
            @Override
            public void run() {
                wv_bbs.reload();
                ptr_bbs.refreshComplete();
            }
        }, 500);
    }
    // 初始化titilebar
    private void initTitlebar() {
        // titlebar
        setTitleBarPadding(mView.findViewById(R.id.title_bar));
        // 设置返回按钮
        mView.findViewById(R.id.title_bar_return).setVisibility(View.GONE);
        // 设置Title
        ((TextView) mView.findViewById(R.id.title_bar_title)).setText("社区");
    }
}
