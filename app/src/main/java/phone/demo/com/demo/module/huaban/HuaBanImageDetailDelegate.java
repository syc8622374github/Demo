package phone.demo.com.demo.module.huaban;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/12/1 0001.
 */

public class HuaBanImageDetailDelegate extends AppDelegate {

    protected HuaBanImageDetailDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
