package phone.demo.com.library.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author cyc
 * @name phone.demo.com.library.view
 * @description 视图层基础接口协议(view delegate base class)
 * @date 2016/9/27 0027
 */
public interface IDelegate {
    //初始化视图layout
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    //获取导航栏
    Toolbar getToolbar();
    //初始化视图
    void initWidget();
    //获取根视图
    View getRootView();
    //获取menuId
    int getOptionsMenuId();
    //获取layout资源文件id
    int getRootLayoutId();

    //是否存在activity
    boolean hasActivity();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 生命周期结束
     */
    void onDestroy();

    /**
     * 指定拥有加载过程的View
     */
    View getLoadingTargetView();
}
