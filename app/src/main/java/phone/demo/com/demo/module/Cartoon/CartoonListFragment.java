package phone.demo.com.demo.module.Cartoon;

import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonListFragment extends BaseFragment<CartoonListDelegate> {
    @Override
    protected CartoonListDelegate createDelegate(Fragment fragment) {
        return new CartoonListDelegate(fragment);
    }
}
