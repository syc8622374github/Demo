package phone.demo.com.demo.module.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.BaseRecyclerAdapter;
import phone.demo.com.demo.adapter.RecyclerShowAPINewsAdapter;
import phone.demo.com.demo.api.ShowApi;
import phone.demo.com.demo.module.cartoon.bean.ShowApiResponse;
import phone.demo.com.demo.module.common.WebViewActivity;
import phone.demo.com.demo.module.news.bean.JokeItemBean;
import phone.demo.com.demo.module.news.bean.JokeResBody;
import phone.demo.com.demo.module.news.bean.NewsItemBean;
import phone.demo.com.demo.module.news.bean.NewsResBody;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.demo.widget.LoadingFooter;
import phone.demo.com.library.util.Logger;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.library.widget.myRecyclerView.HeaderAndFooterRecyclerViewAdapter;
import phone.demo.com.library.widget.myRecyclerView.RecyclerViewUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsListDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Bundle bundle;
    private int mPage = 1;
    private LoadingFooter mFooterView;
    private RecyclerShowAPINewsAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<JokeItemBean> jokeData;

    protected NewsListDelegate(Fragment fragment) {
        super(fragment);
        bundle = fragment.getArguments();
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_huaban_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (bundle.get(Constant.TYPE_KEY).toString()) {
                    case Constant.NEWS_JOKES_TYPE:
                        getJokesData(true);
                        break;
                    default:
                        getNewsData(true);
                        break;
                }
            }
        });
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
        switch (bundle.get(Constant.TYPE_KEY).toString()) {
            case Constant.NEWS_JOKES_TYPE:
                getJokesData(false);
                break;
            default:
                getNewsData(false);
                break;
        }
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerShowAPINewsAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        if (getFootView() != null) {
            RecyclerViewUtils.addFootView(recyclerView, getFootView());
        }
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter.setOnClickItemListener(new BaseRecyclerAdapter.OnAdapterListener() {
            @Override
            public void onItemClickListener(View view, Object itemBean, int position) {
                NewsItemBean newsItemBean = (NewsItemBean) itemBean;
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Constant.URL, newsItemBean.getLink());
                context.startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
                    /*boolean isBottom = staggeredGridLayoutManager
                            .findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount();*/
                    boolean isBottom = linearLayoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount();
                    if (isBottom && !swipeRefreshLayout.isRefreshing()) {
                        switch (bundle.get(Constant.TYPE_KEY).toString()) {
                            case Constant.NEWS_JOKES_TYPE:
                                getJokesOnScroll();
                                break;
                            default:
                                getNewsDataOnScroll();
                                break;
                        }
                    }
                }
            }
        });
    }

    /**
     * 获取新闻api数据
     *
     * @param isRefresh
     */
    public void getNewsData(boolean isRefresh) {
        if (!isRefresh) {
            //varyViewHelper.showLoadingView();
            swipeRefreshLayout.setEnabled(false);
        }
        mPage = 1;
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getNewData(Constant.APPID, Constant.SECRET, bundle.get(Constant.TYPE_KEY).toString(), bundle.get(Constant.TITLE).toString(), "", String.valueOf(mPage++), "0", String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<NewsResBody>>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse<NewsResBody> showApiResponse) {
                        swipeRefreshLayout.setEnabled(true);
                        if (showApiResponse.getShowapi_res_code() == 0 && showApiResponse.getShowapi_res_body() != null) {
                            ArrayList<NewsItemBean> data = showApiResponse.getShowapi_res_body().getPageBean().getContentlist();
                            mAdapter.setListNotify(data);
                        }
                    }
                });
    }

    /**
     * 加载更多新闻api数据
     */
    public void getNewsDataOnScroll() {
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getNewData(Constant.APPID, Constant.SECRET, bundle.get(Constant.TYPE_KEY).toString(), bundle.get(Constant.TITLE).toString(), "", String.valueOf(mPage++), "0", String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<NewsResBody>>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse<NewsResBody> showApiResponse) {
                        swipeRefreshLayout.setEnabled(true);
                        if (showApiResponse.getShowapi_res_code() == 0 && showApiResponse.getShowapi_res_body() != null) {
                            ArrayList<NewsItemBean> data = showApiResponse.getShowapi_res_body().getPageBean().getContentlist();
                            mAdapter.addListNotify(data);
                        }
                    }
                });
    }

    /**
     * 获取笑话大全数据
     *
     * @param isRefresh
     */
    public void getJokesData(boolean isRefresh) {
        if (!isRefresh) {
            swipeRefreshLayout.setEnabled(false);
        }
        mPage = 1;
        if (jokeData == null) {
            jokeData = new ArrayList<>();
        } else {
            jokeData.clear();
        }
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBitmapJokeData(Constant.APPID, Constant.SECRET, "", String.valueOf(mPage), String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<JokeResBody>>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setEnabled(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setEnabled(false);
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse<JokeResBody> bitmapShowApiResponse) {
                        final ArrayList<JokeItemBean> data = bitmapShowApiResponse.getShowapi_res_body().getContentlist();
                        if (bitmapShowApiResponse.getShowapi_res_code() == 0 && data != null) {
                            RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                                    .getGifJokeData(Constant.APPID, Constant.SECRET, String.valueOf(mPage), String.valueOf(10))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<ShowApiResponse<JokeResBody>>() {
                                        @Override
                                        public void onCompleted() {
                                            swipeRefreshLayout.setEnabled(false);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            swipeRefreshLayout.setEnabled(false);
                                            Logger.e(e);
                                        }

                                        @Override
                                        public void onNext(ShowApiResponse<JokeResBody> jokeResBodyShowApiResponse) {
                                            swipeRefreshLayout.setEnabled(true);
                                            if (jokeResBodyShowApiResponse.getShowapi_res_code() == 0 && jokeResBodyShowApiResponse.getShowapi_res_body() != null) {
                                                jokeData.addAll(jokeResBodyShowApiResponse.getShowapi_res_body().getContentlist());
                                                data.set(data.size() / 2 - 1, jokeData.get(mPage++));
                                                data.add(jokeData.get(mPage));
                                                mAdapter.setListNotify(data);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    /**
     * 加载更多笑话大全数据
     */
    public void getJokesOnScroll() {
        System.out.println(mPage);
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBitmapJokeData(Constant.APPID, Constant.SECRET, "", String.valueOf(mPage), String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<JokeResBody>>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setEnabled(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setEnabled(false);
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse<JokeResBody> bitmapShowApiResponse) {
                        final ArrayList<JokeItemBean> data = bitmapShowApiResponse.getShowapi_res_body().getContentlist();
                        if (bitmapShowApiResponse.getShowapi_res_code() == 0 && data != null) {
                            if(jokeData.size()/2<=mPage-1){
                                RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                                        .getGifJokeData(Constant.APPID, Constant.SECRET, String.valueOf(mPage++), String.valueOf(10))
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Subscriber<ShowApiResponse<JokeResBody>>() {
                                            @Override
                                            public void onCompleted() {
                                                swipeRefreshLayout.setEnabled(false);
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                swipeRefreshLayout.setEnabled(false);
                                                Logger.e(e);
                                            }

                                            @Override
                                            public void onNext(ShowApiResponse<JokeResBody> jokeResBodyShowApiResponse) {
                                                swipeRefreshLayout.setEnabled(true);
                                                if (jokeResBodyShowApiResponse.getShowapi_res_code() == 0 && jokeResBodyShowApiResponse.getShowapi_res_body() != null) {
                                                    jokeData.addAll(jokeResBodyShowApiResponse.getShowapi_res_body().getContentlist());
                                                    data.set(data.size() / 2 - 1, jokeData.get((mPage-1)*2 + 1));
                                                    data.add(jokeData.get(((mPage++)-1)*2 + 2));
                                                    mAdapter.addListNotify(data);
                                                }
                                            }
                                        });
                            }else{
                                swipeRefreshLayout.setEnabled(true);
                                data.set(data.size() / 2 - 1, jokeData.get((mPage-1)*2 + 1));
                                data.add(jokeData.get(((mPage++)-1)*2 + 2));
                                mAdapter.addListNotify(data);
                            }
                        }
                    }
                });
    }
}
