package phone.demo.com.demo.module.welcome;

import android.app.Activity;
import android.os.Bundle;

import phone.demo.com.library.base.BaseActivity;

/**
 * Created by cyc on 16/11/21
 * App欢迎页面
 */
public class AppSplashActivity extends BaseActivity<AppSplashDelegate>
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AppSplashDelegate createDelegate(Activity activity) {
        return new AppSplashDelegate(activity);
    }
}
