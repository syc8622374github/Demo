package phone.demo.com.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 80986
 * @name phone.demo.com.demo.adapter
 * @description
 * @date 2016/10/14 0014
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> views;

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
        super(fm);
        this.title = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
