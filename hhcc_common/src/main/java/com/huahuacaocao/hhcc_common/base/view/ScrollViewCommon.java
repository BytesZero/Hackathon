package com.huahuacaocao.hhcc_common.base.view;


import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;


public class ScrollViewCommon extends ScrollView {

	// titlebar
	View title_bar;
	// 主页的根节点
	FrameLayout fl_page;
	private Context mContext;
	// 屏幕的高度
	private int height = 0;

	// 记录按下的Y坐标点
	float mY = 0;
	// 记录按下的Y滚动的距离
	int sY = 0;
	// statebar的高度
	int top = 0;
	// 是否支持透明的StartBar
	boolean isStartBar;

	// 回弹阻尼距离
	private float ScrollY =0;

	public ScrollViewCommon(Context context, AttributeSet attrs,
                            int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		initBounceListView();
	}

	public ScrollViewCommon(Context context) {
		super(context);
		mContext = context;
		initBounceListView();
	}

	public ScrollViewCommon(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initBounceListView();
	}

	private void initBounceListView() {

		final DisplayMetrics metrics = mContext.getResources()
				.getDisplayMetrics();
		height = metrics.heightPixels;
		ScrollY=(float)(height/6);
		Log.e("BasescroView", "height:" + height);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			// 按下时的坐标点
			mY = ev.getY(0);
			// 按下时的滚动距离，判断在哪一屏
			sY = this.getScrollY();
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();

		if (action == MotionEvent.ACTION_UP) {// 手指抬起时
			Log.e("CustomScrollView", "sY:" + sY);
			if (sY < height) {// 在第一屏滑动
				float slideY1 = mY - ev.getY(0);
				if (slideY1 > ScrollY && this.getScrollY() < height) {// 滑动的距离大于ScrollY，并且在第一屏内，则滚动到第二屏
					smoothScrollTo(0, height);
					return true;
				}
				if (slideY1 < ScrollY && this.getScrollY() < height) {// 滑动的距离小于ScrollY，并且在第一屏内，则返回第一屏
					smoothScrollTo(0, 0);
					return true;
				}
			} else {// 在第二屏滑动
				float slideY2 = ev.getY(0) - mY;
				if (slideY2 > ScrollY && this.getScrollY() < height) {// 滑动的距离大于ScrollY，并且在第二屏内，则滚动到第一屏
					smoothScrollTo(0, 0);
					return true;
				}
				if (slideY2 < ScrollY && this.getScrollY() < height) {// 滑动的距离小于ScrollY，并且在第二屏内，则返回第二屏
					smoothScrollTo(0, height);
					return true;
				}
			}

		}

		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// Log.e("CustomScrollView", "SY:" + this.getScrollY());
		// 如果滑动的距离小于屏幕高度的1/2,titlebar显示
		int mySY = this.getScrollY();
		if (mySY < (height / 2)) {
			TitleBarGone(false);
		} else {
			TitleBarGone(true);
		}

		// 到达顶部显示状态栏
		if (mySY == 0 && isStartBar) {
			// 显示状态栏
			fl_page.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
		}
		// 防止抛滑动(如果滚动的距离小于等于屏幕高度，并且按下时的滚动高度大于屏幕高度，则滚动到第二屏的最顶端)
		if (mySY <= height&&sY>height) {
			smoothScrollTo(0, height);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	/**
	 * 设置titlebar和其他的参数
	 * @param title_bar titlebar
	 * @param fl_page 根节点
	 * @param top 状态栏的高度
	 * @param isStartBar 是否支持沉浸式状态栏
	 */
	public void setTitle_bar(View title_bar, FrameLayout fl_page, int top,
			boolean isStartBar) {
		this.title_bar = title_bar;
		this.fl_page = fl_page;
		this.top = top;
		this.isStartBar = isStartBar;
		if (!isStartBar) {
			height = height - top;
		}
	}

	/**
	 * 设置TitleBar隐藏
	 * 
	 * @param isGone
	 */
	private void TitleBarGone(boolean isGone) {
		if (title_bar != null) {
			if (isGone) {
				title_bar.setVisibility(View.GONE);
				if (isStartBar) {
					// 隐藏状态栏
					fl_page.setSystemUiVisibility(View.INVISIBLE);
				}
			} else {
				title_bar.setVisibility(View.VISIBLE);

			}
		}
	}
	
	/**
	 * 禁止ScrollView内布局变化后自动滚动
	 */
	@Override
	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		return 0;
	}
	
	/**
	 * 抛的事件
	 */
	@Override
	public void fling(int velocityY) {
		super.fling(velocityY);
	}

}
