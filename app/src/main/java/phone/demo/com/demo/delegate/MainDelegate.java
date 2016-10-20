package phone.demo.com.demo.delegate;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.MyFragmentPagerAdapter;
import phone.demo.com.demo.fragment.ImageListFragment;
import phone.demo.com.library.view.AppDelegate;

/**
 * @author 80986
 * @name phone.demo.com.demo.delegate
 * @description
 * @date 2016/10/14 0014
 */
public class MainDelegate extends AppDelegate {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles=  new ArrayList<>();

    public MainDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        toolbar = get(R.id.toolbar);
        tabLayout = get(R.id.tabLayout);
        viewPager = get(R.id.viewpager);
    }

    @Override
    public void initData() {
        super.initData();
        titles.add("tab1");
        titles.add("tab2");
        titles.add("tab3");
        fragments.add(ImageListFragment.newInstance("1"));
        fragments.add(ImageListFragment.newInstance("2"));
        fragments.add(ImageListFragment.newInstance("3"));
        viewPager.setOffscreenPageLimit(3);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        viewPager.setAdapter(new MyFragmentPagerAdapter(((AppCompatActivity)activity).getSupportFragmentManager(),titles,fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }
}
