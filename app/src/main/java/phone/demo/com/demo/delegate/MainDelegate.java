package phone.demo.com.demo.delegate;

import android.app.Activity;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
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
    private NavigationView mNavigationView;
    private DrawerLayout drawerLayout;

    public MainDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        toolbar = get(R.id.toolbar);
        tabLayout = get(R.id.tabLayout);
        viewPager = get(R.id.viewpager);
        mNavigationView = get(R.id.nav_view);
        drawerLayout = get(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headerView = mNavigationView.inflateHeaderView(R.layout.nav_header_main);
        disableNavigationViewScrollbars(mNavigationView);
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void initData() {
        super.initData();
        titles = Arrays.asList(context.getResources().getStringArray(R.array.tab_name));
        for(String title :titles){
            fragments.add(ImageListFragment.newInstance(title));
        }
        viewPager.setOffscreenPageLimit(titles.size());//设置防止重复创建和销毁，造成内存溢出
        viewPager.setAdapter(new MyFragmentPagerAdapter(((AppCompatActivity)activity).getSupportFragmentManager(),titles,fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }
}
