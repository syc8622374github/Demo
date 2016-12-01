package phone.demo.com.demo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import phone.demo.com.demo.R;

/**
 * Created by cyc on 2016/11/29 0029.
 */

public class ImageUtils {
    /**
     * 获取图片
     *
     * @return
     */
    public static Bitmap getImage(Bitmap bitmap) {

        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 4096f;//这里设置高度为800f
            float ww = 4096f;//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            //bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            BitmapFactory.decodeByteArray(BitmapUtils.Bitmap2Bytes(bitmap), 0, BitmapUtils.Bitmap2Bytes(bitmap).length, newOpts);
            return compressImage(bitmap, 100);//压缩好比例大小后再进行质量压缩
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image, long maxSize) {
        if (image == null)
            return image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > maxSize) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 保存图片方法
     * @param context
     * @param bm
     * @param cartoonUrl
     * @return
     */
    public static File saveBitmap(Context context, Bitmap bm, String cartoonUrl) {
        File captureFile = null;
        try {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(Environment.getExternalStorageDirectory(), context.getResources().getString(R.string.app_name) + "/cartoon");
                file.mkdirs();
                captureFile = new File(file, DigestUtils.getMD5(cartoonUrl)+ ".jpg");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(captureFile));
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return captureFile;
    }

    /**
     * 获取图片参数
     * @return
     */
    public static BitmapFactory.Options getBitmapOption(){
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        return options;
    }
}
