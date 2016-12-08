package phone.demo.com.demo.module.huaban;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import phone.demo.com.demo.R;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bg.setTransitionName(activity.getIntent().getStringExtra("transition_bundle"));
        }
        Glide.with(activity).load(activity.getIntent().getStringExtra("image_url")).into(bg);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
