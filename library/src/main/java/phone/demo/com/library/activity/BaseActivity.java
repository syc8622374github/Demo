package phone.demo.com.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import phone.demo.com.library.presenter.ActivityPresenter;
import phone.demo.com.library.view.IDelegate;

/**
 * @author cyc
 * @name phone.demo.com.library.activity
 * @description activity 基类 主要职责业务逻辑与数据绑定
 * @date 2016/9/28 0028
 */
public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {

    //protected LinearLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 经测试在代码里直接声明透明状态栏更有效
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }*/
    }
}
