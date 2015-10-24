package com.huahuacaocao.hhcc_common.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 防止listview和gridview、ScrollView的冲突
 * @author zsl
 * @blog http://blog.csdn.net/yy1300326388
 *
 */
public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//防止数据显示不全
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
