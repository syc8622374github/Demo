package phone.demo.com.demo.delegate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.MyFragmentPagerAdapter;
import phone.demo.com.demo.module.huaban.HuaBanImageListFragment;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/3 0003.
 */
public class HuaBanMainDelegate extends AppDelegate {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();

    public HuaBanMainDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_huaban_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tabLayout = get(R.id.tabLayout);
        viewPager = get(R.id.viewpager);
    }

    @Override
    public void initData() {
        super.initData();
        List<String> titles = Arrays.asList(context.getResources().getStringArray(R.array.huaban_titles));
        List<String> keys = Arrays.asList(context.getResources().getStringArray(R.array.huaban_keys));
        for(int i=0;i<titles.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString(Constant.TYPE_KEY,keys.get(i));
            bundle.putString(Constant.TITLE,titles.get(i));
            fragments.add(HuaBanImageListFragment.newInstance(bundle));
        }
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new MyFragmentPagerAdapter(((AppCompatActivity)activity).getSupportFragmentManager(),titles,fragments));
        tabLayout.setupWithViewPager(viewPager);
    }
}
