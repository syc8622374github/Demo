package phone.demo.com.library.view;

/**
 * Created by cyc on 2016/11/23 0023.
 * 加载数据状态接口
 */
interface LoadListener {

    //下拉刷新
    void onRefresh();

    //上拉加载更多
    void onLoadMore();

    //重试加载数据
    void onRetryLoad();
}
