package phone.demo.com.demo.module.common;

import android.app.Activity;

import phone.demo.com.library.base.BaseActivity;

/**
 * Created by cyc on 2016/11/22 0022.
 */

public class WebViewActivity extends BaseActivity<WebViewDelegate> {


    @Override
    protected WebViewDelegate createDelegate(Activity activity) {
        return new WebViewDelegate(activity);
    }
}
