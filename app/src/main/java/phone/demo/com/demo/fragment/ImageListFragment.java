package phone.demo.com.demo.fragment;

import android.support.v4.app.Fragment;

import phone.demo.com.demo.delegate.ImageListDelegate;
import phone.demo.com.library.base.BaseFragment;

/**
 * @author cyc
 * @name phone.demo.com.demo.fragment
 * @description
 * @date 2016/10/17 0017
 */
public class ImageListFragment extends BaseFragment<ImageListDelegate> {
    @Override
    protected ImageListDelegate createDelegate(Fragment fragment) {
        return new ImageListDelegate(fragment);

    }
}
