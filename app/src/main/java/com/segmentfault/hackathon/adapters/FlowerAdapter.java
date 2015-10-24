package com.segmentfault.hackathon.adapters;

import android.content.Context;

import com.huahuacaocao.hhcc_common.base.adapter.UniversalAdapter;
import com.huahuacaocao.hhcc_common.base.adapter.ViewHolder;
import com.huahuacaocao.hhcc_common.base.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.segmentfault.hackathon.R;
import com.segmentfault.hackathon.entity.FlowerEntity;

import java.util.List;


/**
 * 搜索花的Adapter
 * 
 * @author zsl
 *
 */
public class FlowerAdapter extends UniversalAdapter<FlowerEntity> {

	public FlowerAdapter(Context context, List<FlowerEntity> mlists,
                         int layoutId) {
		super(context, mlists, R.layout.lv_search_flower_item);
	}

	@Override
	public void convert(ViewHolder holder, FlowerEntity t, int position) {
		holder.setText(R.id.search_flower_item_tv_name, t.getPlant_name());
		//设置头像
		CircleImageView circleImageView=holder.getView(R.id.search_flower_item_iv_icon);
		ImageLoader.getInstance().displayImage(t.getImages(), circleImageView);
	}

}
