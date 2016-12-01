package phone.demo.com.demo.module.main;

import com.facebook.drawee.backends.pipeline.Fresco;

import phone.demo.com.library.application.BaseApplication;

/**
 * Created by cyc on 2016/11/17 0017.
 */

public class DemoApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        //CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID, false);
    }
}
