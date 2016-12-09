package phone.demo.com.demo.module.huaban;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import phone.demo.com.demo.R;
import phone.demo.com.demo.module.huaban.bean.PinsMainEntity;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.library.base.BaseActivity;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/12/1 0001.
 */

public class HuaBanImageDetailDelegate extends AppDelegate {

    protected HuaBanImageDetailDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ImageView bg = get(R.id.bg);
        PinsMainEntity pinsMainEntity = activity.getIntent().getParcelableExtra("data");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bg.setTransitionName(pinsMainEntity.getFile().getKey());
        }
        Glide.with(activity).load(pinsMainEntity.getLink()).into(bg);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.TYPE_KEY,String.valueOf(pinsMainEntity.getPin_id()));
        ((BaseActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_info_recycler,HuaBanImageDetailFragment.newInstance(bundle)).commit();
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
