package phone.demo.com.library.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phone.demo.com.library.R;
import phone.demo.com.library.util.varyview.VaryViewHelper;
import phone.demo.com.library.view.IDelegate;

/**
 * Created by CYC on 2016/10/16.
 * Presenter base class for Fragment
 * Presenter层的实现基类
 * @param <T> View delegate class type
 */

public abstract class FragmentPresenter<T extends IDelegate> extends Fragment {
    protected T viewDelegate;
    protected VaryViewHelper varyViewHelper;
    private boolean isPrepared;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDelegate.initWidget();
        viewDelegate.initData();
        bindEvenListener();
        if (viewDelegate.getLoadingTargetView() != null) {
            varyViewHelper = new VaryViewHelper.Builder()
                    .setDataView(viewDelegate.getLoadingTargetView())
                    .setLoadingView(LayoutInflater.from(getContext()).inflate(R.layout.layout_loadingview, null))
                    .setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.layout_emptyview, null))
                    .setErrorView(LayoutInflater.from(getContext()).inflate(R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            onRetryListener();
                        }
                    })
                    .build();
            bindEvenListener();
            initPrepare();
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
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
