package phone.demo.com.demo.module.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/12/20 0020.
 */

public class NewsMainFragment extends BaseFragment<NewsMainDelegate> {
    public static NewsMainFragment newInstance(Bundle args) {
        NewsMainFragment fragment = new NewsMainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected NewsMainDelegate createDelegate(Fragment fragment) {
        return new NewsMainDelegate(fragment);
    }
}
