package com.segmentfault.hackathon.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huahuacaocao.hhcc_common.base.BaseFragment;
import com.segmentfault.hackathon.R;

public class QRCodeFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitlebar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    public void initData() {

    }

    // 初始化titilebar
    private void initTitlebar() {
        // titlebar
        setTitleBarPadding(mView.findViewById(R.id.title_bar));
        // 设置返回按钮
        mView.findViewById(R.id.title_bar_return).setVisibility(View.GONE);
        // 设置Title
        ((TextView) mView.findViewById(R.id.title_bar_title)).setText("扫描二维码");
    }
}
