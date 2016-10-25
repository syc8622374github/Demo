package phone.demo.com.demo.delegate;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.ZhiHuListAdapter;
import phone.demo.com.demo.bean.NewsTimeLine;
import phone.demo.com.demo.net.ZhihuApi;
import phone.demo.com.demo.utils.Url;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author cyc
 * @name phone.demo.com.demo.delegate
 * @description
 * @date 2016/10/17 0017
 */
public class ImageListDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private ZhiHuListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;
    private String time;
    private NewsTimeLine timeLine;

    public ImageListDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        //recyclerview 尽量不要使用缓存，当重复使用时会导致类引用问题。
        recyclerView = (RecyclerView) rootView.findViewById(R.id.feed_list);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_images_list_swipe_layout);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
                    R.color.refresh_progress_2, R.color.refresh_progress_3);
            swipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, context.getResources().getDisplayMetrics()));
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (swipeRefreshLayout.isRefreshing()) {
                        initZhiHuData();
                    }
                }
            });
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        adapter.updateLoadStatus(adapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getBeforeNews(time);
                            }
                        }, 1000);
                    }
                }
            }
        });
    }

    private void getBeforeNews(String time) {
        RetrofitUtils.createApi(context, ZhihuApi.class, Url.ZHIHU_BASE_URL)
                .getBeforetNews(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsTimeLine>() {
                    @Override
                    public void call(NewsTimeLine newsTimeLine) {
                        updateNewData(newsTimeLine, ZhiHuListAdapter.LOAD_MORE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                });

    }

    @Override
    public void initData() {
        super.initData();
        initZhiHuData();
    }

    public void initZhiHuData() {
        RetrofitUtils.createApi(context, ZhihuApi.class, Url.ZHIHU_BASE_URL)
                .getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsTimeLine>() {
                    @Override
                    public void call(NewsTimeLine newsTimeLine) {
                        updateNewData(newsTimeLine, ZhiHuListAdapter.LOAD_PULL_TO);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void updateNewData(NewsTimeLine newsTimeLine, int loadState) {
        timeLine = newsTimeLine;
        time = newsTimeLine.getDate();
        if (adapter == null) {
            adapter = new ZhiHuListAdapter(context, timeLine);
            recyclerView.setAdapter(adapter);
            return;
        }
        adapter.updateLoadStatus(loadState);
        adapter.notifyDataSetChanged(newsTimeLine);
    }
}
