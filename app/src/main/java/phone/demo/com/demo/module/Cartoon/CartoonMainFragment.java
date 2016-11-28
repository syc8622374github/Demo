package phone.demo.com.demo.module.cartoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonMainFragment extends BaseFragment<CartoonMainDelegate> {

    public static CartoonMainFragment newInstance(Bundle bundle) {
        CartoonMainFragment fragment = new CartoonMainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected CartoonMainDelegate createDelegate(Fragment fragment) {
        return new CartoonMainDelegate(fragment);
    }
}
