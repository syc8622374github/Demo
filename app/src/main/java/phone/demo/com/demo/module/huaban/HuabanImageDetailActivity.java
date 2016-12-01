package phone.demo.com.demo.module.huaban;

import android.app.Activity;

import phone.demo.com.library.base.BaseActivity;

/**
 * Created by cyc on 2016/12/1 0001.
 */

public class HuaBanImageDetailActivity extends BaseActivity<HuaBanImageDetailDelegate> {
    @Override
    protected HuaBanImageDetailDelegate createDelegate(Activity activity) {
        return new HuaBanImageDetailDelegate(activity);
    }
}
