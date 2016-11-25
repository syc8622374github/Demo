package phone.demo.com.demo.module.Cartoon;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import phone.demo.com.library.base.BaseActivity;

/**
 * Created by cyc on 2016/11/24 0024.
 * 漫画浏览
 */

public class CartoonReadActivity extends BaseActivity<CartoonReadDelegate> {

    public final static String DATA = "image_urls";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected CartoonReadDelegate createDelegate(Activity activity) {
        return new CartoonReadDelegate(activity);
    }
}
