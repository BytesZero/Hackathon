package com.huahuacaocao.hhcc_common.base.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.huahuacaocao.hhcc_common.base.activitys.CropImageActivity;
import com.huahuacaocao.hhcc_common.base.activitys.SelectImageActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片的工具类
 * 
 * @author zsl
 *
 */
public class ImageUtils {
	// URL
	public static String systemUrl = "";

	/**
	 * 获得到此时的URL
	 * 
	 * @return
	 */
	public static Uri getImgTempUri(Context context) {
        String path=Environment.getExternalStorageDirectory().getPath()+"/huahuacaocao_temp";
        File file=new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
        systemUrl=path+"/img_"+System.currentTimeMillis()+"_temp.jpg";
        return Uri.fromFile(new File(systemUrl));
	}

	/**
	 * 获取systemUrl转换为uri
	 * 
	 * @return
	 */
	public static Uri getUriBySystemUrl() {
		return Uri.fromFile(new File(systemUrl));
	}

	/**
	 * 获取Url转换为uri
	 * 
	 * @return
	 */
	public static Uri getUriByUrl(String urlString) {
		return Uri.fromFile(new File(urlString));
	}

	/**
	 * 得到相机的Intent
	 * 
	 * @return
	 */
	public static Intent getIntentFromCamera(Context context) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// action is
																	// capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, getImgTempUri(context));
		return intent;
	}

	/**
	 * 得到相册的Intent
	 * 
	 * @return
	 */
	public static Intent getIntentFromCapture() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
		return intent;
	}

	/**
	 * 得到剪裁图片的Intent
	 * @param uri
	 * @return
	 */
	public static Intent getIntentFromPhotoZoom(Uri uri) {
		Intent intent = new Intent();
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 700);
		intent.putExtra("outputY", 700);
		intent.putExtra("uri", uri); // 图片地址
		return intent;
	}

	/**
	 * 得到剪裁图片的Intent ,包含CropImageActivity
	 * @param context
	 * @param uri
	 * @return
	 */
	public static Intent getIntentFromPhotoZoom(Context context,Uri uri) {
		Intent intent = new Intent(context, CropImageActivity.class);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 700);
		intent.putExtra("outputY", 700);
		intent.putExtra("uri", uri); // 图片地址
		return intent;
	}

    /**
     * 得到选择图片的intent
     * @param context 上下文
     * @param isSelect0 是否启动相机（0:启动想起1:启动相册）
     * @return
     */
    public static Intent getIntentFromSelectImage(Context context,int isSelect0) {
        Intent intent = new Intent(context, SelectImageActivity.class);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("isSelect0",isSelect0);
        return intent;
    }

	// public static Intent getIntentFromPhotoZoom(Uri uri) {
	// Intent intent = new Intent("com.android.camera.action.CROP");
	// intent.setDataAndType(uri, "image/*");
	// intent.putExtra("crop", "true");
	// // aspectX aspectY 是宽高的比例
	// intent.putExtra("aspectX", 1);
	// intent.putExtra("aspectY", 1);
	// // outputX outputY 是裁剪图片宽高
	// intent.putExtra("outputX", 700);
	// intent.putExtra("outputY", 700);
	// intent.putExtra("scale", true);
	// intent.putExtra("return-data", false);
	// intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
	// intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
	// intent.putExtra("noFaceDetection", true); // no face detection
	// return intent;
	// }

	/**
	 * Bitmap对象保存味图片文件
	 * 
	 * @param bitmap
	 * @return
	 */
	public static String saveBitmapFile(Context context, Bitmap bitmap) {
		String path = "editplantinfo_"+System.currentTimeMillis()+"_temp.jpg";
		try {
			FileOutputStream fos = context.openFileOutput(path,
					Context.MODE_PRIVATE);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
			path = context.getFilesDir() + "/" + path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
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
			return null;
		}
		return bitmap;
	}

	/**
	 * 设置imageview的图片,通过ImageLoader
	 * 
	 * @param url
	 *            url地址
	 * @param imageView
	 *            imageview
	 */
	public static void setImageView(String url, ImageView imageView) {
		// 设置ImageView
		ImageLoader.getInstance().displayImage(url, imageView);
	}

}
