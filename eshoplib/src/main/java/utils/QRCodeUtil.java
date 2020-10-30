package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * 二维码工具
 */
public class QRCodeUtil {


    public static void createQRCode(final Context context, final String content, final ImageView iv) {
        //匿名内部类导致Activity内存泄漏的问题待解决
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                System.out.println("ds>>>  生成二维码成功");
                return QRCodeEncoder.syncEncodeQRCode(content, BGAQRCodeUtil.dp2px(context, 150));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    iv.setImageBitmap(bitmap);

                } else {

                    Toast.makeText(context, "生成二维码失败", Toast.LENGTH_SHORT).show();

                }
            }
        }.execute();
    }

}
