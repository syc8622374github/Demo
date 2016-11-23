package phone.demo.com.demo.module.Cartoon;

import android.support.v4.app.Fragment;

import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class BlackAndWhiteDelegate extends AppDelegate {

    protected BlackAndWhiteDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return 0;
    }
}
