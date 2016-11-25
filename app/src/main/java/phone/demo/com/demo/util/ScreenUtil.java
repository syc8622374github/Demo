package phone.demo.com.demo.util;

import android.content.Context;
import android.opengl.GLES10;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author cyc
 * @name phone.demo.com.demo.utils
 * @description
 * @date 2016/10/19 0019
 */
public class ScreenUtil {
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dp2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }

    //added by Jack for handle exception "Bitmap too large to be uploaded into a texture".

    /**
     * 判断图片是否大于设备最大加载大小
     * @param with
     * @param height
     * @return
     */
    public static boolean isOutSizeOfMaxLoadingBitmapSize(int with, int height) {
        int[] maxSize = new int[1];
        GLES10.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxSize, 0);

        if(maxSize[0] < height || maxSize[0] < with) {
            return true;
        }

        return false;
    }
}
