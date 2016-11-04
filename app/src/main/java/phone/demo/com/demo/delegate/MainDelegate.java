package phone.demo.com.demo.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import phone.demo.com.demo.MainActivity;
import phone.demo.com.demo.R;
import phone.demo.com.demo.module.huaban.HuaBanImageListFragment;
import phone.demo.com.demo.module.huaban.HuaBanMainFragment;
import phone.demo.com.demo.util.Constant;
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
    //private List<String> titles=  new ArrayList<>();
    private NavigationView mNavigationView;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private int[] navigationIcons;
    private String[] titleList;
    private int currentTabIndex = 0;//fragment切换选中选项

    public MainDelegate(Activity activity) {
        super(activity);
        fragmentManager = ((MainActivity)activity).getSupportFragmentManager();
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
        titleList = activity.getResources().getStringArray(R.array.main_navigation_menu);
        navigationIcons = activity.getResources().getIntArray(R.array.main_navigation_menu_icons);
        intiMenuView();
        toggle.syncState();
        mNavigationView.inflateHeaderView(R.layout.nav_header_main);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getGroupId() == R.id.menu_group_type) {
                    selectFragments(item.getItemId());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        disableNavigationViewScrollbars(mNavigationView);
    }

    /**
     * 手动填充Menu 方便以后对menu的调整
     */
    private void intiMenuView() {
        Menu menu = mNavigationView.getMenu();
        int order = 0;
        for (String title : titleList) {
//            menu.add(Menu.NONE, order++, Menu.NONE, title).setIcon(R.drawable.ic_menu_share).setCheckable(true);
            menu.add(R.id.menu_group_type, order++, Menu.NONE, title).setIcon(navigationIcons[0]).setCheckable(true);
        }
//        menu.addSubMenu("text");
        //menu.getItem(0).setChecked(true);//默认选中第一项
    }

    /**
     * 去除左划导航栏滚动条
     * 
     * @param navigationView
     */
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
        //titles = Arrays.asList(context.getResources().getStringArray(R.array.tab_name));
        Bundle bundle = new Bundle();
        bundle.putString(Constant.TYPE_KEY,"all");
        bundle.putString(Constant.TITLE,"首页");
        fragments.add(HuaBanImageListFragment.newInstance(bundle));
        fragments.add(HuaBanMainFragment.newInstance(new Bundle()));
        selectFragments(0);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    public void selectFragments(int position) {
        FragmentTransaction trx = fragmentManager.beginTransaction();
        trx.hide(fragments.get(currentTabIndex));
        if (!fragments.get(position).isAdded())
        {
            trx.add(R.id.container_with_refresh, fragments.get(position));
        }
        trx.show(fragments.get(position)).commit();
        currentTabIndex = position;
    }

}
