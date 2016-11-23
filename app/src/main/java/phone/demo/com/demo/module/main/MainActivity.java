package phone.demo.com.demo.module.main;

import android.app.Activity;
import android.os.Bundle;

import phone.demo.com.demo.delegate.MainDelegate;
import phone.demo.com.library.base.BaseActivity;

public class MainActivity extends BaseActivity<MainDelegate> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainDelegate createDelegate(Activity activity) {
        return new MainDelegate(activity);
    }
}
