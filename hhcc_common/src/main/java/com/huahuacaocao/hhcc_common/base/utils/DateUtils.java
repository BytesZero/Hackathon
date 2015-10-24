package com.huahuacaocao.hhcc_common.base.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class DateUtils {
	static SimpleDateFormat sY_M_D_TZ = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS Z");
	static SimpleDateFormat sY_M_D = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sYMD = new SimpleDateFormat("yyyy年MM月dd日");
	static SimpleDateFormat sHMS = new SimpleDateFormat("HH:mm:ss");
	static SimpleDateFormat sHM = new SimpleDateFormat("HH:mm");

	/**
	 * long类型转换为年月日类型
	 * 
	 * @param time
	 * @return
	 */
	public static String longToyearmonthday(long time) {
		String timeString = sYMD.format(time);
		return timeString;
	}

	/**
	 * long类型转换为年-月-日类型
	 * 
	 * @param time
	 * @return
	 */
	public static String longToY_M_d(long time) {
		String timeString = sY_M_D.format(time);
		return timeString;
	}

	/**
	 * long类型转换为时:分:秒
	 * 
	 * @param time
	 * @return
	 */
	public static String longTohourmins(long time) {
		String timeString = sHMS.format(time);
		return timeString;
	}

	/**
	 * long 类型转换为：分：秒
	 * 
	 * @param time
	 * @return
	 */
	public static String longhourmin(long time) {
		String timeString = sHM.format(time);
		return timeString;
	}

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSS Z 格式转为long
	 * 
	 * @param date
	 * @return
	 */
	public static long longYMDTZ(String date) {
		try {
			return sY_M_D_TZ.parse((date.replace("Z", " UTC"))).getTime();
		} catch (ParseException e) {
			return System.currentTimeMillis();
		}
	}

    /**
     * 获得已经成长的天数
     * @param date
     * @return
     */
    public static long getDay(String date) {
        long createTime=longYMDTZ(date);
        long dayTime=System.currentTimeMillis()-createTime;
        return (long) Math.ceil(dayTime/86400000);
    }

}
