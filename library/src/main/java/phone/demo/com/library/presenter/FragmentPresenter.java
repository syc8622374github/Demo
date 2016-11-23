package phone.demo.com.library.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phone.demo.com.library.view.IDelegate;

/**
 * Created by CYC on 2016/10/16.
 * Presenter base class for Fragment
 * Presenter层的实现基类
 * @param <T> View delegate class type
 */

public abstract class FragmentPresenter<T extends IDelegate> extends Fragment {
    protected T viewDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate = createDelegate(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        viewDelegate.create(inflater,container,savedInstanceState);
        return viewDelegate.getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDelegate.initWidget();
        viewDelegate.initVaryView();
        viewDelegate.initData();
        bindEvenListener();
    }

    /**
     * fragment生命周期销毁所做操作
     * 1.清空handler队列
     * 3.释放视图代理层支援
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate.onDestroy();
        viewDelegate = null;
    }

    /*--------------------开放方法--------------------*/

    /**
     * 创建视图协议类
     * @return
     */
    protected abstract T createDelegate(Fragment fragment);

    /**
     * 如果有设置loadingView，加载失败时重试点击的回调
     *
     * @see IDelegate #getLoadingTargetView()
     */
    protected void onRetryListener() {}

    /**
     * 初始化一些监听等
     */
    protected void bindEvenListener() {
    }

    /**
     * 当fragment首次可见时回调
     */
    protected void onFirstUserVisible() {
    }
}
