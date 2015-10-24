package com.huahuacaocao.hhcc_common.base.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.huahuacaocao.hhcc_common.base.BaseActivity;
import com.huahuacaocao.hhcc_common.base.config.CodeList;
import com.huahuacaocao.hhcc_common.base.utils.ImageUtils;

/**
 * Created by zsl on 15/10/14.
 * 选择图片的中转Activity
 * 0：拍照
 * 1:相册选取
 */
public class SelectImageActivity extends BaseActivity {

    // 图片的路径
    Uri imageUri = null;
    //是否是拍照
    int isSelect0;
    //零时的路径
    String url_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    // 初始化数据
    public void initData() {
        isSelect0 = getIntent().getIntExtra("isSelect0", 0);
        //如果是0则是拍照，否则是相册选取
        if (isSelect0 == 0) {
            startActivityForResult(
                    ImageUtils.getIntentFromCamera(SelectImageActivity.this),
                    CodeList.CAMERA);
        } else {
            startActivityForResult(
                    ImageUtils.getIntentFromCapture(),
                    CodeList.CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }
        // 拍照
        if (requestCode == CodeList.CAMERA) {
            imageUri = ImageUtils.getUriByUrl(ImageUtils.systemUrl);
            // 剪裁
            startActivityForResult(ImageUtils.getIntentFromPhotoZoom(mActivity, imageUri), CodeList.CROP);
        }
        if (data == null)
            return;
        // 相册
        if (requestCode == CodeList.CAPTURE) {

            imageUri = data.getData();
            // 剪裁
            startActivityForResult(ImageUtils.getIntentFromPhotoZoom(mActivity, imageUri), CodeList.CROP);
        }
        // 处理剪裁结果并放回
        if (requestCode == CodeList.CROP) {
            url_temp = data.getStringExtra("url");
            Log.e("SelectImageActivity", "url:" + url_temp);
            Intent intent = new Intent();
            intent.setData(Uri.parse(url_temp));
            setResult(RESULT_OK, intent);
            finish();
        }
    }


}
