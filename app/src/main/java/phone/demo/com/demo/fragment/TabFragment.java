package phone.demo.com.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import phone.demo.com.demo.delegate.TabDelegate;
import phone.demo.com.library.base.BaseFragment;

/**
 * @author 80986
 * @name phone.demo.com.demo.fragment
 * @description
 * @date 2016/10/14 0014
 */
public class TabFragment extends BaseFragment<TabDelegate> {

    private View view;

    public static TabFragment newInstance(String data) {

        Bundle args = new Bundle();
        args.putString("title",data);

        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TabDelegate createDelegate(Fragment fragment) {
        return new TabDelegate(fragment);
    }
}
