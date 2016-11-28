package phone.demo.com.demo.module.cartoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonListFragment extends BaseFragment<CartoonListDelegate> {

    public static CartoonListFragment newInstance(Bundle args) {
        CartoonListFragment fragment = new CartoonListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected CartoonListDelegate createDelegate(Fragment fragment) {
        return new CartoonListDelegate(fragment);
    }
}
