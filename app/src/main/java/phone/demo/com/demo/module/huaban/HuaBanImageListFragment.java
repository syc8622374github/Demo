package phone.demo.com.demo.module.huaban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import phone.demo.com.library.presenter.FragmentPresenter;

/**
 * Created by cyc on 2016/11/3 0003.
 */

public class HuaBanImageListFragment extends FragmentPresenter<HuaBanImageListDelegate> {

    public static HuaBanImageListFragment newInstance(Bundle bundle) {
        HuaBanImageListFragment fragment = new HuaBanImageListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected HuaBanImageListDelegate createDelegate(Fragment fragment) {
        return new HuaBanImageListDelegate(fragment);
    }
}
