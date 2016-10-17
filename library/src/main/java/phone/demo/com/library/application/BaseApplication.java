package phone.demo.com.library.application;

import android.app.Application;
import android.content.Context;

/**
 * @author cyc
 * @name phone.demo.com.library
 * @description application基类。
 * @date 2016/9/27 0027
 */
public class BaseApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
