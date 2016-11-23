package phone.demo.com.demo.module.huaban;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import phone.demo.com.library.base.BaseFragment;

/**
 * Created by cyc on 2016/11/3 0003.
 */

public class HuaBanMainFragment extends BaseFragment<HuaBanMainDelegate> {

    public static HuaBanMainFragment newInstance(Bundle args) {
        HuaBanMainFragment fragment = new HuaBanMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HuaBanMainDelegate createDelegate(Fragment fragment) {
        return new HuaBanMainDelegate(fragment);
    }
}
