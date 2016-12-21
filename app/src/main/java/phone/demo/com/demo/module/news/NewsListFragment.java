package phone.demo.com.demo.module.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsListFragment extends BaseFragment<NewsListDelegate> {
    public static NewsListFragment newInstance(Bundle bundle) {
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected NewsListDelegate createDelegate(Fragment fragment) {
        return new NewsListDelegate(fragment);
    }
}
