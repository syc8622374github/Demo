package phone.demo.com.library.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phone.demo.com.library.R;
import phone.demo.com.library.util.varyview.VaryViewHelper;

/**
 * @author cyc
 * @name phone.demo.com.library.view
 * @description 视图代理层基类
 * @date 2016/9/27 0027
 */
public abstract class AppDelegate implements IDelegate,LoadListener{
    //视图管理器
    protected final SparseArray<View> mViews = new SparseArray<>();

    protected View rootView;
    protected Context context;
    //ActivityPresenter类
    protected Activity activity;
    //FragmentPresenter类
    protected Fragment fragment;
    protected VaryViewHelper varyViewHelper;

    protected AppDelegate(Fragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
        this.context = fragment.getContext();
    }

    protected AppDelegate(Activity activity) {
        this.activity = activity;
        context = activity;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootLayoutId(), container, false);
        context = rootView.getContext();
    }

    public final <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    @Override
    public void initWidget() {
    }

    @Override
    public void initVaryView(){
        if (getLoadingTargetView() != null) {
            varyViewHelper = new VaryViewHelper.Builder()
                    .setDataView(getLoadingTargetView())
                    .setLoadingView(View.inflate(context, R.layout.layout_loadingview, null))
                    .setEmptyView(View.inflate(context, R.layout.layout_emptyview, null))
                    .setErrorView(View.inflate(context, R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryLoad();
                        }
                    })
                    .build();
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRetryLoad() {

    }

    @Override
    public VaryViewHelper getVaryView() {
        return varyViewHelper;
    }

    @Override
    public boolean hasActivity() {
        return activity != null ? true : false;
    }

    @Override
    public View getRootView() {
        return rootView;
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void onDestroy() {
        if (getVaryView()!=null)
            getVaryView().releaseVaryView();
    }

    @Override
    public void initData() {

    }

    /**
     * 视图管理
     *
     * bug:
     * 使用缓存视图对于某些视图控件会产生引用问题，照成视图控件失效。
     *
     * @param id
     * @param <T>
     * @return
     * @deprecated
     */
    protected <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) activity.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    @Override
    public View getLoadingTargetView() {
        return null;
    }

    /*---------开放方法-----------*/
    /*//获取layout资源文件id
    public abstract int getRootLayoutId();*/
}
