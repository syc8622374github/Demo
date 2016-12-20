package phone.demo.com.demo.module.news;

import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/12/20 0020.
 */

public class NewsMainFragment extends BaseFragment<NewsMainDelegate> {
    @Override
    protected NewsMainDelegate createDelegate(Fragment fragment) {
        return new NewsMainDelegate(fragment);
    }
}
