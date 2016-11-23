package phone.demo.com.demo.module.Cartoon;

import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonMainFragment extends BaseFragment<CartoonMainDelegate> {
    @Override
    protected CartoonMainDelegate createDelegate(Fragment fragment) {
        return new CartoonMainDelegate(fragment);
    }
}
