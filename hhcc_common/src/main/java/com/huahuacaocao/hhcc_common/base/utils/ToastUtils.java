package com.huahuacaocao.hhcc_common.base.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	/**
	 * 显示短吐司
	 * @param context
	 * @param message
	 */
	public static void showShortToast(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 显示长吐司
	 * @param context
	 * @param message 
	 */
	public static void showLongToast(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
