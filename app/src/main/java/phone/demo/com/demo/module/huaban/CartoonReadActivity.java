package phone.demo.com.demo.module.huaban;

import android.app.Activity;

import phone.demo.com.library.base.BaseActivity;

/**
 * Created by cyc on 2016/11/24 0024.
 * 漫画浏览
 */

public class CartoonReadActivity extends BaseActivity<CartoonReadDelegate> {

    @Override
    protected CartoonReadDelegate createDelegate(Activity activity) {
        return new CartoonReadDelegate(activity);
    }
}
