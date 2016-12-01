package phone.demo.com.demo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.Method;

import phone.demo.com.demo.bean.PermissionFail;
import phone.demo.com.demo.bean.PermissionSuccess;
import phone.demo.com.library.util.Logger;


/**
 * 权限工具类
 */
public class PermissionUtil {

    public static void needPermission(Fragment context, int reqCode, String... permissions) {
        needPermission(context.getActivity(), reqCode, permissions);
    }

    public static void needPermission(Activity context, int reqCode, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //6.0以下版本不需要代码申请权限
            executeSuccessResult(context, reqCode);
        }

        boolean granted = hasPermission(context, permissions);//检查权限
        if (granted) {
            //已获得权限
            executeSuccessResult(context, reqCode);
        } else {
            //申请权限
            ActivityCompat.requestPermissions(context, permissions, reqCode);
        }
    }

    private static void executeSuccessResult(Object context, int reqCode) {
        Method successMethod = getTargetMethod(context, reqCode,PermissionSuccess.class);
        try {
            if(successMethod!=null){
                successMethod.invoke(context);
            }
        } catch (Exception e) {
            Logger.e(e);
        }
    }

    private static void executeFailResult(Object context, int reqCode) {
        Method successMethod = getTargetMethod(context, reqCode,PermissionFail.class);
        try {
            successMethod.invoke(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Method getTargetMethod(Object context, int reqCode,Class annotation) {
        Method[] declaredMethods = context.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!method.isAccessible()) {
                method.setAccessible(true); //私有的方法必须强制
            }
            //判断方法上是否使用了目标注解
            boolean annotationPresent = method.isAnnotationPresent(annotation);
            if (annotationPresent) {
                if (isTargetMethod(method,reqCode,annotation)) { //比较requestCode是否相等
                    return method;
                }
            }
        }
        return null;
    }
    private static boolean isTargetMethod(Method method, int reqCode, Class cls){
        if(cls.equals(PermissionSuccess.class)){
            return  reqCode == method.getAnnotation(PermissionSuccess.class).requestCode();
        }else if(cls.equals(PermissionFail.class)){
            return  reqCode == method.getAnnotation(PermissionFail.class).requestCode();
        }
        return false;
    }


    private static boolean hasPermission(Context context, String... permissions) {
        for (String permission : permissions) {
            int granted = ContextCompat.checkSelfPermission(context, permission);
            if (granted == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    public static void onRequestPermissionsResult(Fragment context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        onRequestPermissionsResult(context, requestCode, permissions, grantResults);
    }

    public static void onRequestPermissionsResult(Activity context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permissionGranted = true;
        for (int grant : grantResults) {
            if (grant == PackageManager.PERMISSION_DENIED) {
                permissionGranted = false;
                break;
            }
        }
        if (permissionGranted) {
            //获得权限
            executeSuccessResult(context, requestCode);
        } else {
            //权限被用户拒绝
            executeFailResult(context, requestCode);
        }
    }
}
