package phone.demo.com.demo.module.cartoon;

import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class BlackAndWhiteCartoonFragment extends BaseFragment<BlackAndWhiteDelegate> {
    @Override
    protected BlackAndWhiteDelegate createDelegate(Fragment fragment) {
        return new BlackAndWhiteDelegate(fragment);
    }
}
