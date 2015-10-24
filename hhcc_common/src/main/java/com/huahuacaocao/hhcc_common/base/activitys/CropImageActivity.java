package com.huahuacaocao.hhcc_common.base.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.huahuacaocao.hhcc_common.R;
import com.huahuacaocao.hhcc_common.base.BaseActivity;
import com.huahuacaocao.hhcc_common.base.utils.DisplayUtils;
import com.huahuacaocao.hhcc_common.base.utils.ImageUtils;
import com.huahuacaocao.hhcc_common.base.utils.ToastUtils;
import com.huahuacaocao.hhcc_common.base.view.ClipImageLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.FileNotFoundException;

/**
 * 剪裁图片的Activity
 * 
 * @author zsl
 *
 */
public class CropImageActivity extends BaseActivity {
	private ClipImageLayout mClipImageLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        initTitlebar();
        initView();
        initEvent();
        initData();

    }

    // 初始化titilebar
	private void initTitlebar() {
		// titlebar
		setTitleBarPadding(findViewById(R.id.title_bar));
		// 设置返回按钮
		ImageView iv_return = (ImageView) findViewById(R.id.title_bar_return);

		setWidth(iv_return);

		iv_return.setImageResource(R.drawable.common_titlebar_button_cancel);
		iv_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//播放声音
				finish();
			}
		});
		// 设置Title
		((TextView) findViewById(R.id.title_bar_title)).setText("缩放和剪裁");
		// 设置返回按钮
		ImageView iv_finish = (ImageView) findViewById(R.id.title_bar_more);
		iv_finish.setVisibility(View.VISIBLE);
		setWidth(iv_finish);
		iv_finish.setImageResource(R.drawable.common_titlebar_button_ok);
		iv_finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//播放声音
				Bitmap bitmap = mClipImageLayout.clip();

				String urlString = ImageUtils.saveBitmapFile(CropImageActivity.this, bitmap);
				Intent intent = new Intent();
				intent.putExtra("url", urlString);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	// 初始化View
	private void initView() {
		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);

	}

	// 初始化事件
	private void initEvent() {

	}

	// 初始化数据
	public void initData() {
		Uri uri = (Uri) getIntent().getExtras().get("uri");
		ImageLoader.getInstance().loadImage(uri + "",
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String arg0, View arg1) {

					}

					@Override
					public void onLoadingFailed(String arg0, View arg1,
							FailReason arg2) {
						ToastUtils.showLongToast(CropImageActivity.this, "图片加载错误");
					}

					@Override
					public void onLoadingComplete(String arg0, View arg1,
							Bitmap bitmap) {
						// 设置bitmap
						mClipImageLayout.setImage(bitmap);
					}

					@Override
					public void onLoadingCancelled(String arg0, View arg1) {

					}
				});

		mClipImageLayout.setmHorizontalPadding(0);
	}

	// 设置宽度
	private void setWidth(ImageView iv_return) {
		LayoutParams ivLayoutParams = iv_return.getLayoutParams();
		ivLayoutParams.width = DisplayUtils.dpToPx(CropImageActivity.this, 68);
		ivLayoutParams.height = LayoutParams.MATCH_PARENT;
		iv_return.setLayoutParams(ivLayoutParams);
	}

	/**
	 * Uri 转换为bitmap
	 * 
	 * @param context
	 * @param uri
	 * @return
	 */
	public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
