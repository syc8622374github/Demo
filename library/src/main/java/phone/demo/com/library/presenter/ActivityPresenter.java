package phone.demo.com.library.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import phone.demo.com.library.view.IDelegate;

/**
 * @author cyc
 * @name phone.demo.com.library.presenter
 * @description presenter 基类
 * @date 2016/9/27 0027
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {
    protected T viewDelegate;
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected Toolbar toolbar;
    private static boolean isShowToolbar = true;

    /**
     * 初始化toolbar
     */
    protected void initToolbar(){
        if(getToolbarAvailable()){
            toolbar = viewDelegate.getToolbar();
            if(toolbar!=null&&getToolbarAvailable()){
                setSupportActionBar(toolbar);
            }
        }
    }

    /**
     * 导航栏菜单创建
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate = createDelegate(this);
        viewDelegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(viewDelegate.getRootLayoutId());
        initToolbar();
        viewDelegate.initWidget();
        viewDelegate.initVaryView();
        viewDelegate.initData();
        bindEvenListener();
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * activity生命周期销毁所做操作
     * 1.清空handler队列
     * 3.释放视图代理层支援
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate.onDestroy();
        viewDelegate = null;
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 当应用被回收后重新启动应用需要重新初始化下视图代理器
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = createDelegate(this);
            } catch (Exception e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    /*--------------------开放方法--------------------*/

    /**
     * 创建视图协议类
     * @return
     */
    protected abstract T createDelegate(Activity activity);

    /**
     * 初始化一些监听等
     */
    protected void bindEvenListener() {
    }

    /**
     * 接收bundle回调
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 是否启用toolBar
     */
    protected boolean getToolbarAvailable() {
        return isShowToolbar;
    }

    /**
     * only Iconify use
     */
    protected void onUseIconifySetMenuItem(Menu menu) {
    }

    /**
     * 如果有设置loadingView，加载失败时重试点击的回调
     *
     * @see phone.demo.com.library.view.IDelegate #getLoadingTargetView()
     */
    protected void onRetryListener() {
    }
}
