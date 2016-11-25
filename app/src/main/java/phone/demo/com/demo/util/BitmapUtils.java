package phone.demo.com.demo.util;

import android.graphics.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by cyc on 2016/11/25 0025.
 */

public class BitmapUtils {
    /**
     * bitmap 转 byte
     * @param bitmap
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        return out.toByteArray();
    }

    /**
     * bitmap 转换成 InputStream
     * @param bm
     * @return
     */
    public static InputStream Bitmap2IS(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
        return sbs;
    }
}
