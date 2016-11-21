package phone.demo.com.demo.module.welcome;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import phone.demo.com.demo.R;
import phone.demo.com.demo.module.main.MainActivity;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/21 0021.
 */

public class AppSplashDelegate extends AppDelegate {

    private ImageView mSplashImage;
    private static final int ANIMATION_TIME = 2000;
    private static final float SCALE_END = 1.13F;

    protected AppSplashDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mSplashImage = get(R.id.splash_image);
    }

    @Override
    public void initData() {
        super.initData();
        mSplashImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        },1000);
    }

    private void startAnim()
    {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
