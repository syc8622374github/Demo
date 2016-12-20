package phone.demo.com.demo.module.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/12/20 0020.
 */

public class NewsMainDelegate extends AppDelegate {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    protected NewsMainDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_tab_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
    }

    @Override
    public void initData() {
        super.initData();
        /*RetrofitUtils.createShowApi(context, ShowApi.class,ShowApi.API)
                .getNewsTypeData(Constant.APPID,Constant.SECRET)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Sub)*/
    }
}
